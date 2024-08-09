package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;

import java.util.List;

public interface JodalPlanService {
    void save(JodalPlanDTO dto);
    JodalPlanDTO update(JodalPlanDTO dto);
    void deleteById(Long id);
    JodalPlanDTO findById(Long id);

}
