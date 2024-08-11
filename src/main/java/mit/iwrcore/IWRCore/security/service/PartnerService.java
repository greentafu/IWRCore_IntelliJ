package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Partner;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.PartnerDTO;
public interface PartnerService {

    Partner findPartnerEntity(Long pno, String id, String reg_number);
    PartnerDTO findPartnerDto(Long pno, String id, String reg_number);

    PageResultDTO<PartnerDTO, Partner> findPartnerList(PageRequestDTO pageRequestDTO);

    Integer insertPartner(PartnerDTO dto);

    void deletePartner(Long pno);

    PartnerDTO partnerTodto(Partner entity);
    Partner partnerDtoToEntity(PartnerDTO dto);
}
