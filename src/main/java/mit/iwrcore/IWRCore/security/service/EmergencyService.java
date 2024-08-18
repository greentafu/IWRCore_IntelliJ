package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Emergency;
import mit.iwrcore.IWRCore.security.dto.EmergencyDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;

import java.util.List;
import java.util.Optional;

public interface EmergencyService {
    // DTO를 엔티티로 변환
    Emergency convertToEntity(EmergencyDTO dto);

    // 엔티티를 DTO로 변환
    EmergencyDTO convertToDTO(Emergency entity);

    // 기타 CRUD 메서드
    EmergencyDTO createEmergency(EmergencyDTO emergencyDTO);

    Optional<EmergencyDTO> getEmergencyById(Long id);

    EmergencyDTO updateEmergency(Long id, EmergencyDTO emergencyDTO);

    void deleteEmergency(Long id);

    PageResultDTO<EmergencyDTO, Emergency> getAllEmergencies(PageRequestDTO requestDTO);

    List<EmergencyDTO> getEmergencyByBalju(Long baljuNo);
}