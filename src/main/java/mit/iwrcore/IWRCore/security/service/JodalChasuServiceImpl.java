package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.JodalChasu;
import mit.iwrcore.IWRCore.repository.JodalChasuRepository;
import mit.iwrcore.IWRCore.repository.JodalPlanRepository;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class JodalChasuServiceImpl implements JodalChasuService{
//    private final JodalChasuRepository jodalChasuRepository;
//    private final JodalPlanRepository jodalPlanRepository;
//    @Override
//    public void save(JodalChasuDTO dto) {
//        JodalChasu jodalChasu = dtoToEntity(dto);
//        jodalChasuRepository.save(jodalChasu);
//    }
//
//    @Override
//    public JodalChasuDTO update(JodalChasuDTO dto) {
//        JodalChasu jodalChasu = dtoToEntity(dto);
//        JodalChasu updatedJodalChasu = jodalChasuRepository.save(jodalChasu);
//        return entityToDTO(updatedJodalChasu);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        jodalChasuRepository.deleteById(id);
//    }
//
//    @Override
//    public JodalChasuDTO findById(Long id) {
//        Optional<JodalChasu> jodalChasu = jodalChasuRepository.findById(id);
//        return jodalChasu.map(this::entityToDTO).orElse(null);
//    }
//
//    @Override
//    public List<JodalChasuDTO> findByJodalPlanId(Long jodalPlanId) {
//        List<JodalChasu> jodalChasus = jodalChasuRepository.findByJodalPlan_JoNo(jodalPlanId);
//        return jodalChasus.stream()
//                .map(this::entityToDTO)
//                .collect(Collectors.toList());
//    }
//
//    private JodalChasu dtoToEntity(JodalChasuDTO dto) {
//        return JodalChasu.builder()
//                .joNum(dto.getJoNum())
//                .joDate(dto.getJoDate())
//                .jodalPlan(jodalPlanRepository.findById(dto.getJodalPlanId()).orElse(null))
//                .build();
//    }
//
//    private JodalChasuDTO entityToDTO(JodalChasu entity) {
//        return JodalChasuDTO.builder()
//                .joNum(entity.getJoNum())
//                .joDate(entity.getJoDate())
//                .jodalPlanId(entity.getJodalPlan() != null ? entity.getJodalPlan().getJoNo() : null)
//                .build();
//    }
}

