package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.JodalChasu;
import mit.iwrcore.IWRCore.entity.JodalPlan;
import mit.iwrcore.IWRCore.entity.Structure;
import mit.iwrcore.IWRCore.repository.JodalChasuRepository;

import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ProPlanSturctureDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.QuantityDateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class JodalChasuServiceImpl implements JodalChasuService {

    private final JodalChasuRepository jodalChasuRepository;
    private final MemberService memberService;
    private final JodalPlanService jodalPlanService;
    private final StructureService structureService;

    @Override
    public JodalChasuDTO convertToDTO(JodalChasu entity) {
        return JodalChasuDTO.builder()
                .jcnum(entity.getJcnum())
                .joNum(entity.getJoNum())
                .joDate(entity.getJoDate())
                .memberDTO(memberService.memberTodto(entity.getWriter())) // Assuming memberService has this method
                .jodalPlanDTO(jodalPlanService.entityToDTO(entity.getJodalPlan())) // Assuming jodalPlanService has this method
                .build();
    }

    @Override
    public JodalChasu convertToEntity(JodalChasuDTO dto) {
        return JodalChasu.builder()
                .jcnum(dto.getJcnum())
                .joNum(dto.getJoNum())
                .joDate(dto.getJoDate())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO())) // Assuming memberService has this method
                .jodalPlan(jodalPlanService.dtoToEntity(dto.getJodalPlanDTO())) // Assuming jodalPlanService has this method
                .build();
    }

    @Override
    public JodalChasuDTO createJodalChasu(JodalChasuDTO dto) {
        JodalChasu entity = convertToEntity(dto);
        JodalChasu savedEntity = jodalChasuRepository.save(entity);
        return convertToDTO(savedEntity);
    }

    @Override
    public JodalChasuDTO getJodalChasuById(Long jcnum) {
        return convertToDTO(jodalChasuRepository.findById(jcnum).get());
    }

    @Override
    public JodalChasuDTO updateJodalChasu(JodalChasuDTO dto) {
        JodalChasuDTO jodalChasuDTO=createJodalChasu(dto);
        return jodalChasuDTO;
//        if (!jodalChasuRepository.existsById(id)) {
//            throw new RuntimeException("ID가 " + id + "인 JodalChasuDTO를 찾을 수 없습니다.");
//        }
//        JodalChasu entity = convertToEntity(dto);
//        entity.setJoNum(id); // 수정할 때 ID를 설정합니다.
//        JodalChasu updatedEntity = jodalChasuRepository.save(entity);
//        return convertToDTO(updatedEntity);
    }

    @Override
    public void deleteJodalChasu(Long id) {
        if (!jodalChasuRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 JodalChasuDTO를 찾을 수 없습니다.");
        }
        jodalChasuRepository.deleteById(id);
    }

    @Override
    public void deleteJodalChasuByPlan(Long joNo){
        List<JodalChasu> list=jodalChasuRepository.getJodalChausFromPlan(joNo);
        list.forEach(x->deleteJodalChasu(x.getJcnum()));
    }

    @Override
    public List<JodalChasuDTO> getAllJodalChasus() {
        return jodalChasuRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuantityDateDTO> partnerMainJodal(Long jodalplanId, LocalDateTime baljuDate, Long make) {
        List<JodalChasu> entityList=jodalChasuRepository.getJodalChausFromPlan(jodalplanId);
        List<QuantityDateDTO> list=new ArrayList<>();

        for(JodalChasu jodalChasu:entityList){
            QuantityDateDTO quantityDateDTO= QuantityDateDTO.builder()
                    .quantity(jodalChasu.getJoNum())
                    .dueDate(jodalChasu.getJoDate())
                    .build();
            list.add(quantityDateDTO);
        }
        LocalDateTime now=LocalDateTime.now();

        long entire1= ChronoUnit.DAYS.between(baljuDate, list.get(0).getDueDate()); // 전체 일수
        long current1= ChronoUnit.DAYS.between(baljuDate, now)+1; // 현재까지의 일수
        Float temp1=Float.parseFloat(String.format("%.2f", current1*100f/entire1));
        if(temp1<0) list.get(0).setPercent(0f);
        else if(temp1>100) list.get(0).setPercent(100f);
        else list.get(0).setPercent(temp1);

        for(int i=0; i<list.size()-1; i++){
            long entire2= ChronoUnit.DAYS.between(list.get(i).getDueDate(), list.get(i+1).getDueDate()); // 전체 일수
            long current2=ChronoUnit.DAYS.between(list.get(i).getDueDate(), now)+1; // 현재까지의 일수
            Float temp2=Float.parseFloat(String.format("%.2f", current2*100f/entire2));
            if(temp2<0) list.get(i+1).setPercent(0f);
            else if(temp2>100) list.get(i+1).setPercent(100f);
            else list.get(i+1).setPercent(temp2);
        }

        Long totalOrder=0L;
        Long sum=(make!=null)?make:0L;

        for(int i=0; i<list.size(); i++){
            long entire=list.get(i).getQuantity();
            totalOrder+=entire;
            if(sum>=entire){
                list.get(i).setCount(100.0f);
                sum-=entire;
            }else if(sum<entire){
                list.get(i).setCount(Float.parseFloat(String.format("%.2f", sum*100f/entire)));
                sum=0L;
            }
        }
        list.get(0).setTotalOrder(totalOrder);

        return list;
    }
    @Override
    public List<ProPlanSturctureDTO> modifyJodalChasu(Long proplanNo) {
        List<Object[]> list = jodalChasuRepository.modifyJodalChasu(proplanNo);
        List<ProPlanSturctureDTO> dtoList = list.stream().map(this::proPlanSturctureToDTO).toList();
        return dtoList;
    }
    private ProPlanSturctureDTO proPlanSturctureToDTO(Object[] objects) {
        JodalPlan jodalPlan = (JodalPlan) objects[0];
        Structure structure = (Structure) objects[1];
        Long tempSumRequest = (Long) objects[2];
        Long tempSumShip = (Long) objects[3];
        JodalChasu jodalChasu=(JodalChasu) objects[4];
        Long countContract=(Long) objects[5];

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+jodalPlan.getJoNo()+":"+countContract);

        StructureDTO structureDTO = (structure != null) ? structureService.structureTodto(structure) : null;
        Long sumRequest = (tempSumRequest != null) ? tempSumRequest : 0L;
        Long sumShip = (tempSumShip != null) ? tempSumShip : 0L;
        JodalPlanDTO jodalPlanDTO = (jodalPlan != null) ? jodalPlanService.entityToDTO(jodalPlan) : null;
        System.out.println(jodalPlanDTO);
        ProplanDTO proplanDTO = (jodalPlanDTO != null) ? jodalPlanDTO.getProplanDTO() : null;
        JodalChasuDTO jodalChasuDTO=(jodalChasu!=null)?convertToDTO(jodalChasu):null;
        Long sumContract=(countContract!=null)?countContract:0L;

        return new ProPlanSturctureDTO(proplanDTO, structureDTO, sumRequest, sumShip, jodalPlanDTO, jodalChasuDTO, sumContract);
    }

    @Override
    public List<JodalChasuDTO> findJCfromJP(Long joNo){
        List<JodalChasu> entityList=jodalChasuRepository.findJCfromJP(joNo);
        List<JodalChasuDTO> dtoList=entityList.stream().map(this::convertToDTO).toList();
        return dtoList;
    }
}