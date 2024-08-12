package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Returns;
import mit.iwrcore.IWRCore.security.dto.ReturnsDTO;

import java.util.List;
import java.util.Optional;

public interface ReturnsService {
    // DTO를 엔티티로 변환
    Returns convertToEntity(ReturnsDTO dto);

    // 엔티티를 DTO로 변환
    ReturnsDTO convertToDTO(Returns entity);

    // 기타 CRUD 메서드
    ReturnsDTO createReturns(ReturnsDTO returnsDTO);

    Optional<ReturnsDTO> getReturnsById(Long id);

    ReturnsDTO updateReturns(Long id, ReturnsDTO returnsDTO);

    void deleteReturns(Long id);

    List<ReturnsDTO> getAllReturns();
}