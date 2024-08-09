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
    private final ContractRepository contractRepository;
    private final JodalChasuRepository jodalChasuRepository;
    private final ProplanRepository proPlanRepository;


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

    private JodalPlan dtoToEntity(JodalPlanDTO dto) {
        return JodalPlan.builder()
                .joNo(dto.getJoNo())
                .details(dto.getDetails())
                .planDate(dto.getPlanDate())
                .writer(Member.builder().id(String.valueOf(dto.getWriterId())).build())
                .contract(contractRepository.findById(dto.getContractId()).orElse(null))
                .jodalChasus(dto.getJodalChasuIds().stream()
                        .map(id -> jodalChasuRepository.findById(id).orElse(null))
                        .collect(Collectors.toList()))
                .proPlan(proPlanRepository.findById(dto.getProPlanId()).orElse(null))
                .build();
    }

    private JodalPlanDTO entityToDTO(JodalPlan entity) {
        return JodalPlanDTO.builder()
                .joNo(entity.getJoNo())
                .details(entity.getDetails())
                .planDate(entity.getPlanDate())
                .writerId(entity.getWriter() != null ? entity.getWriter().getId() : null)
                .contractId(entity.getContract() != null ? entity.getContract().getConNo() : null)
                .jodalChasuIds(entity.getJodalChasus().stream()
                        .map(chasu -> chasu.getJoNum()) // getJoNum() 메서드가 존재하는지 확인
                        .collect(Collectors.toList()))
                .proPlanId(entity.getProPlan() != null ? entity.getProPlan().getProplanNo() : null) // getId() 메서드가 존재하는지 확인
                .build();
        }
}
