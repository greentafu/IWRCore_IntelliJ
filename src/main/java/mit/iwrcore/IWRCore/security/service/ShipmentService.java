package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Shipment;
import mit.iwrcore.IWRCore.security.dto.ShipmentDTO;

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
    ShipmentDTO createShipmentWithoutInvoice();
    ShipmentDTO linkShipmentToInvoice(Long shipmentId, Long invoiceId);
}