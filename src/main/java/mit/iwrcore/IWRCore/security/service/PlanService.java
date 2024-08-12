package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Plan;
import mit.iwrcore.IWRCore.entity.ProPlan;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.security.dto.PlanDTO;

import java.util.List;

public interface PlanService {

    void save(PlanDTO dto);
    void update(PlanDTO planDTO);
    void deleteById(Long id);
    List<PlanDTO> findByProductId(Long productId);

    Plan dtoToEntity(PlanDTO dto);
    PlanDTO entityToDTO(Plan entity);
}
