package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.Shipment;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.PartnerDTO;
import mit.iwrcore.IWRCore.security.dto.ShipmentDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ShipmentGumsuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ShipmentReturn2DTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ShipmentReturnDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShipmentService {
    // DTO를 엔티티로 변환
    Shipment convertToEntity(ShipmentDTO dto);

    // 엔티티를 DTO로 변환
    ShipmentDTO convertToDTO(Shipment entity);

    // 기타 CRUD 메서드
    ShipmentDTO createShipment(ShipmentDTO shipmentDTO);

    ShipmentDTO getShipmentById(Long id);

    ShipmentDTO updateShipment(Long id, ShipmentDTO shipmentDTO);

    void deleteShipment(Long id);

    List<ShipmentDTO> getAllShipments();

    @Transactional
    void updateShipmentWithReturns(Long shipmentId, Long returnsId);

    ShipmentDTO createShipmentWithoutInvoice();
    ShipmentDTO linkShipmentToInvoice(Long shipmentId, Long invoiceId);


    ShipmentReturnDTO findShipment(Long shipNo);
    Shipment findShipmentEntity(Long shipNo);

    void updateShipmentDate(LocalDateTime dateTime, Long shipNo);
    void updateMemberCheck(Member member, Long shipNo);

    List<ShipmentDTO> getShipmentByBalju(Long baljuNo);
    List<ShipmentDTO> canInvoiceShipment();
    List<PartnerDTO> canInvoicePartner();

    PageResultDTO<ShipmentGumsuDTO, Object[]> pageShipment(PageRequestDTO requestDTO);

    PageResultDTO<ShipmentDTO, Shipment> noneInvoiceShipment(PageRequestDTO requestDTO);

}