package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Gumsu;
import mit.iwrcore.IWRCore.entity.Partner;
import mit.iwrcore.IWRCore.security.dto.GumsuDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.BaljuGumsuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.BaljuJodalChasuDTO;

import java.util.List;
import java.util.Optional;

public interface GumsuService {

    // DTO를 엔티티로 변환
    Gumsu convertToEntity(GumsuDTO dto);

    // 엔티티를 DTO로 변환
    GumsuDTO convertToDTO(Gumsu entity);

    // 기타 CRUD 메서드
    GumsuDTO createGumsu(GumsuDTO gumsuDTO);

    GumsuDTO getGumsuById(Long id);

    GumsuDTO updateGumsu(Long id, GumsuDTO gumsuDTO);

    void deleteGumsu(Long id);

    List<GumsuDTO> getAllGumsus();

    PageResultDTO<BaljuGumsuDTO, Object[]> couldGumsu(PageRequestDTO requestDTO);

    Long getQuantityMake(Long baljuNo);

    List<Partner> getNonGumsuPartner();

    List<BaljuJodalChasuDTO> getNoneGumsuBalju(Long pno);
}