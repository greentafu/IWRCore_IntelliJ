package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.JodalChasu;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;

import java.util.List;
import java.util.Optional;

public interface JodalChasuService {
    JodalChasuDTO convertToDTO(JodalChasu entity);

    JodalChasu convertToEntity(JodalChasuDTO dto);

    JodalChasuDTO createJodalChasu(JodalChasuDTO dto);

    Optional<JodalChasuDTO> getJodalChasuById(Long id);

    JodalChasuDTO updateJodalChasu(Long id, JodalChasuDTO dto);

    void deleteJodalChasu(Long id);

    List<JodalChasuDTO> getAllJodalChasus();
//    void save(JodalChasuDTO dto);
//    JodalChasuDTO update(JodalChasuDTO dto);
//    void deleteById(Long id);
//    JodalChasuDTO findById(Long id);
//    List<JodalChasuDTO> findByJodalPlanId(Long jodalPlanId);
}
