package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.JodalPlan;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.repository.ContractRepository;
import mit.iwrcore.IWRCore.repository.JodalChasuRepository;
import mit.iwrcore.IWRCore.repository.JodalPlanRepository;
import mit.iwrcore.IWRCore.repository.ProplanRepository;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class JodalPlanServiceImpl implements JodalPlanService {
    private final JodalPlanRepository jodalPlanRepository;

    private final MemberService memberService;
    private final ProplanService proplanService;
    private final MaterialService materialService;

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
}
