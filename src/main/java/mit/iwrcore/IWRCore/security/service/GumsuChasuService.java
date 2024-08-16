package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.GumsuChasu;
import mit.iwrcore.IWRCore.security.dto.GumsuChasuDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface GumsuChasuService {
    // DTO를 엔티티로 변환
    GumsuChasu convertToEntity(GumsuChasuDTO dto);

    // 엔티티를 DTO로 변환
    GumsuChasuDTO convertToDTO(GumsuChasu entity);

    // 기타 CRUD 메서드
    GumsuChasuDTO createGumsuChasu(GumsuChasuDTO gumsuChasuDTO);

    GumsuChasuDTO getGumsuChasuById(Long id);

    GumsuChasuDTO updateGumsuChasu(Long id, GumsuChasuDTO gumsuChasuDTO);

    void deleteGumsuChasu(Long id);

    PageResultDTO<GumsuChasuDTO, GumsuChasu> getAllGumsuChasus(PageRequestDTO requestDTO);

    List<Object[]> PartnerMain(Long pno);
}