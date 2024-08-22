package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.JodalPlanRepository;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ProPlanSturctureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
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
                    .planDate(LocalDateTime.now())
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
        Optional<JodalPlan> jodalPlan = jodalPlanRepository.findById(id);
        return jodalPlan.map(this::entityToDTO).orElse(null);
    }

    @Override
    public List<ProPlanSturctureDTO> newJodalChasu(Long proplanNo) {
        List<Object[]> list = jodalPlanRepository.stock(proplanNo);
        List<ProPlanSturctureDTO> dtoList = list.stream().map(this::proPlanSturctureToDTO).toList();
        return dtoList;
    }

    private ProPlanSturctureDTO proPlanSturctureToDTO(Object[] objects) {
        ProPlan proPlan = (ProPlan) objects[0];
        Structure structure = (Structure) objects[1];
        Long tempSumRequest = (Long) objects[2];
        Long tempSumShip = (Long) objects[3];
        JodalPlan jodalPlan = (JodalPlan) objects[4];

        ProplanDTO proplanDTO = (proPlan != null) ? proplanService.entityToDTO(proPlan) : null;
        StructureDTO structureDTO = (structure != null) ? structureService.structureTodto(structure) : null;
        Long sumRequest = (tempSumRequest != null) ? tempSumRequest : 0L;
        Long sumShip = (tempSumShip != null) ? tempSumShip : 0L;
        JodalPlanDTO jodalPlanDTO = (jodalPlan != null) ? entityToDTO(jodalPlan) : null;

        return new ProPlanSturctureDTO(proplanDTO, structureDTO, sumRequest, sumShip, jodalPlanDTO);
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
    public JodalPlan dtoToEntity(JodalPlanDTO dto) {
        return JodalPlan.builder()
                .joNo(dto.getJoNo())
                .planDate(dto.getPlanDate())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO()))
                .proPlan(proplanService.dtoToEntity(dto.getProplanDTO()))
                .material(materialService.materdtoToEntity(dto.getMaterialDTO()))
                .build();
    }

    @Override
    public JodalPlanDTO entityToDTO(JodalPlan entity) {
        return JodalPlanDTO.builder()
                .joNo(entity.getJoNo())
                .planDate(entity.getPlanDate())
                .memberDTO(memberService.memberTodto(entity.getWriter()))
                .proplanDTO(proplanService.entityToDTO(entity.getProPlan()))
                .materialDTO(materialService.materTodto(entity.getMaterial()))
                .build();
    }

    @Override
    public List<JodalPlanDTO> noneContractJodalPlan(){
        List<JodalPlan> entityList=jodalPlanRepository.noneContractJodalPlan();
        List<JodalPlanDTO> dtoList=entityList.stream().map(this::entityToDTO).toList();
        return dtoList;
    }
}
