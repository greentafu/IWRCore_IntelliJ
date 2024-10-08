package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.JodalChasu;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ProPlanSturctureDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.QuantityDateDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JodalChasuService {
    JodalChasuDTO convertToDTO(JodalChasu entity);

    JodalChasu convertToEntity(JodalChasuDTO dto);

    JodalChasuDTO createJodalChasu(JodalChasuDTO dto);

    JodalChasuDTO getJodalChasuById(Long id);

    JodalChasuDTO updateJodalChasu(JodalChasuDTO dto);

    void deleteJodalChasu(Long id);

    void deleteJodalChasuByPlan(Long joNo);

    List<JodalChasuDTO> getAllJodalChasus();
    // 조달계획에 따른 조달차수 리스트
    List<QuantityDateDTO> partnerMainJodal(Long jodalplanId, LocalDateTime baljuDate, Long make);
//    void save(JodalChasuDTO dto);
//    JodalChasuDTO update(JodalChasuDTO dto);
//    void deleteById(Long id);
//    JodalChasuDTO findById(Long id);
//    List<JodalChasuDTO> findByJodalPlanId(Long jodalPlanId);
    List<ProPlanSturctureDTO> modifyJodalChasu(Long proplanNo);

    List<JodalChasuDTO> findJCfromJP(Long joNo);
}
