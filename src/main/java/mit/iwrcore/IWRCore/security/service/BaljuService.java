package mit.iwrcore.IWRCore.security.service;


import mit.iwrcore.IWRCore.entity.Balju;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractBaljuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractJodalChasyDTO;

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
    BaljuDTO getBaljuById(Long id);

    // 기존 BaljuDTO 수정
    BaljuDTO updateBalju(Long id, BaljuDTO baljuDTO);

    // ID로 BaljuDTO 삭제
    void deleteBalju(Long id);

    // 모든 BaljuDTO 조회
    List<BaljuDTO> getAllBaljus();

    // 발주완료 리스트
    PageResultDTO<BaljuDTO, Balju> finishedBalju(PageRequestDTO2 requestDTO);
    // 발주완료 리스트 새로
    PageResultDTO<ContractBaljuDTO, Object[]> finBaljuPage(PageRequestDTO2 requestDTO);
    // 계약서 리스트+발주여부
    PageResultDTO<ContractBaljuDTO, Object[]> finishedContract(PageRequestDTO2 requestDTO);
    // 계약서 리스트+발주해야할 것만
    PageResultDTO<ContractBaljuDTO, Object[]> couldBalju(PageRequestDTO requestDTO);

    // 협력회사용 발주서 목록
    PageResultDTO<BaljuDTO, Balju> partnerBaljuList(PageRequestDTO requestDTO);
    // 협력회사 발주서 목록
    List<BaljuDTO> partListBalju(Long pno);
}