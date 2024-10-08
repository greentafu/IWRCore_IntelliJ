package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.JodalPlanRepository;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.JodalChasuDateDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.JodalPlanJodalChsuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ProPlanSturctureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class JodalPlanServiceImpl implements JodalPlanService {
    private final JodalPlanRepository jodalPlanRepository;

    private final MemberService memberService;
    private final ProplanService proplanService;
    private final MaterialService materialService;
    private final StructureService structureService;

    // Proplan 받으면 해당 제품의 구성(자재)이 조달계획 저장됨
    // 조달차수가 존재하면 조달계획 등록한 거고, 조달차수가 존재하지 않으면 조달계획 등록 하지 않은 것
    @Override
    public void saveFromProplan(ProplanDTO proplanDTO, MemberDTO memberDTO) {
        Long product_id = proplanDTO.getProductDTO().getManuCode();
        List<StructureDTO> structureDTOS = structureService.findByProduct_ManuCode(product_id);

        structureDTOS.forEach(x -> {
            JodalPlanDTO jodalPlanDTO = JodalPlanDTO.builder()
                    .memberDTO(memberDTO)
                    .proplanDTO(proplanDTO)
                    .materialDTO(x.getMaterialDTO())
                    .build();
            save(jodalPlanDTO);
        });

    }

    @Override
    public void save(JodalPlanDTO dto) {
        JodalPlan jodalPlan = dtoToEntity(dto);
        jodalPlanRepository.save(jodalPlan);
    }

    @Override
    public JodalPlanDTO update(JodalPlanDTO dto) {
        JodalPlan jodalPlan = dtoToEntity(dto);
        JodalPlan updatedJodalPlan = jodalPlanRepository.save(jodalPlan);
        return entityToDTO(updatedJodalPlan);
    }

    @Override
    public void deleteById(Long id) {
        jodalPlanRepository.deleteById(id);
    }

    @Override
    public JodalPlanDTO findById(Long id) {
        JodalPlanDTO jodalPlanDTO=null;
        if(id!=null) {
            JodalPlan jodalPlan=(JodalPlan) jodalPlanRepository.getJodalPlan(id).get(0)[0];
            jodalPlanDTO=entityToDTO(jodalPlan);
        }
        return jodalPlanDTO;
    }

    @Override
    public List<ProPlanSturctureDTO> newJodalChasu(Long proplanNo) {
        List<Object[]> list = jodalPlanRepository.stock2(proplanNo);
        List<ProPlanSturctureDTO> dtoList = list.stream().map(this::proPlanSturctureToDTO).toList();
        return dtoList;
    }
    private ProPlanSturctureDTO proPlanSturctureToDTO(Object[] objects) {
        JodalPlan jodalPlan = (JodalPlan) objects[0];
        Structure structure = (Structure) objects[1];
        Long tempSumRequest = (Long) objects[2];
        Long tempSumShip = (Long) objects[3];

        StructureDTO structureDTO = (structure != null) ? structureService.structureTodto(structure) : null;
        Long sumRequest = (tempSumRequest != null) ? tempSumRequest : 0L;
        Long sumShip = (tempSumShip != null) ? tempSumShip : 0L;
        JodalPlanDTO jodalPlanDTO = (jodalPlan != null) ? entityToDTO(jodalPlan) : null;
        System.out.println(jodalPlanDTO);
        ProplanDTO proplanDTO = (jodalPlanDTO != null) ? jodalPlanDTO.getProplanDTO() : null;

        return new ProPlanSturctureDTO(proplanDTO, structureDTO, sumRequest, sumShip, jodalPlanDTO, null, null);
    }



    // 조달차수 없는(조달계획 필요한) 자재
    @Override
    public PageResultDTO<JodalPlanDTO, JodalPlan> nonJodalplanMaterial(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("joNo").descending());
        Page<JodalPlan> entityPage = jodalPlanRepository.nonPlanMaterial(pageable);
        Function<JodalPlan, JodalPlanDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(entityPage, fn);
    }
    @Override
    public PageResultDTO<JodalPlanDTO, Object[]> nonJodalplanMaterial2(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("joNo").descending());
        Page<Object[]> entityPage = jodalPlanRepository.nonPlanMaterial2(pageable);
        return new PageResultDTO<>(entityPage, this::exJodalPlan);
    }
    private JodalPlanDTO exJodalPlan(Object[] objects){
        JodalPlan jodalPlan=(JodalPlan) objects[0];
        JodalPlanDTO jodalPlanDTO=(jodalPlan!=null)?entityToDTO(jodalPlan):null;
        return jodalPlanDTO;
    }

    @Override
    public JodalPlan dtoToEntity(JodalPlanDTO dto) {
        return JodalPlan.builder()
                .joNo(dto.getJoNo())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO()))
                .proPlan(proplanService.dtoToEntity(dto.getProplanDTO()))
                .material(materialService.materdtoToEntity(dto.getMaterialDTO()))
                .build();
    }

    @Override
    public JodalPlanDTO entityToDTO(JodalPlan entity) {
        // MemberDTO 변환
        MemberDTO memberDTO = memberService.memberTodto(entity.getWriter());

        // ProPlanDTO 변환
        ProplanDTO proplanDTO = proplanService.entityToDTO(entity.getProPlan());

        // MaterialDTO 변환
        MaterialDTO materialDTO = materialService.materTodto(entity.getMaterial());

        // JodalPlanDTO로 변환
        return JodalPlanDTO.builder()
                .joNo(entity.getJoNo())
                .memberDTO(memberDTO) // MemberDTO 설정
                .proplanDTO(proplanDTO) // ProPlanDTO 설정
                .materialDTO(materialDTO) // MaterialDTO 설정
                .build();
    }


    @Override
    public List<JodalPlanDTO> noneContractJodalPlan(){
        List<Object[]> entityList=jodalPlanRepository.noneContractJodalPlan();
        List<JodalPlanDTO> dtoList=entityList.stream().map(this::exJodalPlanDTO).toList();
        return dtoList;
    }
    private JodalPlanDTO exJodalPlanDTO(Object[] objects){
        JodalPlan jodalPlan=(JodalPlan) objects[0];
        JodalPlanDTO jodalPlanDTO=(jodalPlan!=null)? entityToDTO(jodalPlan):null;
        return jodalPlanDTO;
    }

    @Override
    public Long newNoneJodalChasuCount(){
        Long count=jodalPlanRepository.newNoneJodalPlanCount();
        return (count!=null)?count:0L;
    }

    @Override
    public PageResultDTO<JodalPlanDTO, Object[]> noContract(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("joNo").descending());
        Page<Object[]> entityPage = jodalPlanRepository.noContract(pageable);
        return new PageResultDTO<>(entityPage, this::exJodalPlanDTO);
    }

    @Override
    public List<JodalPlanDTO> findJodalPlanByProPlan(Long proplanNo){
        List<JodalPlan> entityList=jodalPlanRepository.findByProPlanProplanNo(proplanNo);
        List<JodalPlanDTO> dtoList=entityList.stream().map(this::entityToDTO).toList();
        return dtoList;
    }

    @Override
    public List<JodalPlanJodalChsuDTO> noneContract(){
        List<Object[]> entityList=jodalPlanRepository.noneContractJodalPlan();
        List<JodalPlanJodalChsuDTO> dtoList=entityList.stream().map(this::exJodalPlanJodalChsuDTO).toList();
        return dtoList;
    }
    private JodalPlanJodalChsuDTO exJodalPlanJodalChsuDTO(Object[] objects){
        JodalPlan jodalPlan=(JodalPlan) objects[0];
        Long allJodalChasuNum=(Long) objects[1];

        JodalPlanDTO jodalPlanDTO=(jodalPlan!=null)? entityToDTO(jodalPlan):null;
        System.out.println(jodalPlanDTO);
        Long allNum=(allJodalChasuNum!=null)?allJodalChasuNum:0L;
        return new JodalPlanJodalChsuDTO(jodalPlanDTO, allNum);
    }

}
