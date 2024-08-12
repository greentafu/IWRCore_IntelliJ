package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.*;
import mit.iwrcore.IWRCore.security.dto.ShipmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService{
    private final ShipmentRepository shipmentRepository;
    private final InvoiceService invoiceService;
    private final BaljuService baljuService;
    private final MemberService memberService;

    @Override
    public Shipment convertToEntity(ShipmentDTO dto) {
        return Shipment.builder()
                .shipNO(dto.getShipNO())
                .shipNum(dto.getShipNum())
                .receipt(dto.getReceipt())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO())) // MemberDTO를 Member로 변환
                .returns(dto.getReturnsId())
                .invoice(invoiceService.convertToEntity(dto.getInvoiceDTO())) // InvoiceDTO를 Invoice로 변환
                .balju(baljuService.convertToEntity(dto.getBaljuDTO()))       // BaljuDTO를 Balju로 변환
                .build();
    }

    @Override
    public ShipmentDTO convertToDTO(Shipment entity) {
        return ShipmentDTO.builder()
                .shipNO(entity.getShipNO())
                .shipNum(entity.getShipNum())
                .receipt(entity.getReceipt())
                .returnsId(entity.getReturns()) // Returns를 ID로 변환
                .invoiceDTO(invoiceService.convertToDTO(entity.getInvoice())) // Invoice를 InvoiceDTO로 변환
                .baljuDTO(baljuService.convertToDTO(entity.getBalju()))       // Balju를 BaljuDTO로 변환
                .memberDTO(memberService.memberTodto(entity.getWriter()))   // Member를 MemberDTO로 변환
                .build();
    }

    @Override
    public ShipmentDTO createShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = convertToEntity(shipmentDTO);
        Shipment savedShipment = shipmentRepository.save(shipment);
        return convertToDTO(savedShipment);
    }

    @Override
    public Optional<ShipmentDTO> getShipmentById(Long id) {
        return shipmentRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ShipmentDTO updateShipment(Long id, ShipmentDTO shipmentDTO) {
        if (!shipmentRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 ShipmentDTO를 찾을 수 없습니다.");
        }
        Shipment shipment = convertToEntity(shipmentDTO);
        shipment.setShipNO(id); // 수정할 때 ID를 설정합니다.
        Shipment updatedShipment = shipmentRepository.save(shipment);
        return convertToDTO(updatedShipment);
    }

    @Override
    public void deleteShipment(Long id) {
        if (!shipmentRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 ShipmentDTO를 찾을 수 없습니다.");
        }
        shipmentRepository.deleteById(id);
    }

    @Override
    public List<ShipmentDTO> getAllShipments() {
        return shipmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}