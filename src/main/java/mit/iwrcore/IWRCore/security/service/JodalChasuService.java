package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;

import java.util.List;

public interface JodalChasuService {
    void save(JodalChasuDTO dto);
    JodalChasuDTO update(JodalChasuDTO dto);
    void deleteById(Long id);
    JodalChasuDTO findById(Long id);
    List<JodalChasuDTO> findByJodalPlanId(Long jodalPlanId);
}
