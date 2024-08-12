package mit.iwrcore.IWRCore.security.service;


import mit.iwrcore.IWRCore.entity.Balju;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;

import java.util.List;
import java.util.Optional;

public interface BaljuService {
    // DTO를 엔티티로 변환
    Balju convertToEntity(BaljuDTO dto);

    // 엔티티를 DTO로 변환
    BaljuDTO convertToDTO(Balju entity);

    // 새로운 BaljuDTO 생성
    BaljuDTO createBalju(BaljuDTO baljuDTO);

    // ID로 BaljuDTO 조회
    Optional<BaljuDTO> getBaljuById(Long id);

    // 기존 BaljuDTO 수정
    BaljuDTO updateBalju(Long id, BaljuDTO baljuDTO);

    // ID로 BaljuDTO 삭제
    void deleteBalju(Long id);

    // 모든 BaljuDTO 조회
    List<BaljuDTO> getAllBaljus();
}