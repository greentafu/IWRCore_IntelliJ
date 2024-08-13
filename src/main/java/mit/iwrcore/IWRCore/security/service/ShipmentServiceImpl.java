package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.*;
import mit.iwrcore.IWRCore.security.dto.ReturnsDTO;
import mit.iwrcore.IWRCore.security.dto.ShipmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final InvoiceService invoiceService;
    private final BaljuService baljuService;
    private final MemberService memberService;
    private final InvoiceRepository invoiceRepository;
    private final ReturnsRepository returnsRepository;

    @Override
    @Transactional
    public void updateShipmentWithReturns(Long shipmentId, Long returnsId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
        Returns returns = returnsRepository.findById(returnsId)
                .orElseThrow(() -> new RuntimeException("Returns not found"));

        // 출고와 반품 연결 설정
        shipment.setReturns(returns);
        returns.setShipment(shipment);

        shipmentRepository.save(shipment);
        returnsRepository.save(returns);
    }

    @Override
    @Transactional
    public ShipmentDTO createShipmentWithoutInvoice() {
        Shipment shipment = new Shipment();
        // 기본 설정 작업 필요
        Shipment savedShipment = shipmentRepository.save(shipment);
        return convertToDTO(savedShipment);
    }

    @Override
    @Transactional
    public ShipmentDTO linkShipmentToInvoice(Long shipmentId, Long invoiceId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        shipment.setInvoice(invoice);
        Shipment updatedShipment = shipmentRepository.save(shipment);
        return convertToDTO(updatedShipment);
    }

    @Override
    public Shipment convertToEntity(ShipmentDTO dto) {
        Returns returns = dto.getReturnsDTO() != null
                ? returnsRepository.findById(dto.getReturnsDTO().getReNO()).orElse(null)
                : null;

        return Shipment.builder()
                .shipNO(dto.getShipNO())
                .shipNum(dto.getShipNum())
                .receipt(dto.getReceipt())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO()))
                .returns(returns) // Returns 엔티티로 변환
                .invoice(dto.getInvoiceDTO() != null ? invoiceService.convertToEntity(dto.getInvoiceDTO()) : null)
                .balju(dto.getBaljuDTO() != null ? baljuService.convertToEntity(dto.getBaljuDTO()) : null)
                .build();
    }

    @Override
    public ShipmentDTO convertToDTO(Shipment entity) {
        ReturnsDTO returnsDTO = entity.getReturns() != null
                ? ReturnsDTO.builder()
                .reNO(entity.getReturns().getReNO())
                .build()
                : null;
        return ShipmentDTO.builder()
                .shipNO(entity.getShipNO())
                .shipNum(entity.getShipNum())
                .receipt(entity.getReceipt())
                .returnsDTO(returnsDTO) // ReturnsDTO를 빌더로 생성
                .invoiceDTO(entity.getInvoice() != null ? invoiceService.convertToDTO(entity.getInvoice()) : null)
                .baljuDTO(entity.getBalju() != null ? baljuService.convertToDTO(entity.getBalju()) : null)
                .memberDTO(memberService.memberTodto(entity.getWriter()))
                .build();
    }

    @Override
    @Transactional
    public ShipmentDTO createShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = convertToEntity(shipmentDTO);
        Shipment savedShipment = shipmentRepository.save(shipment);
        return convertToDTO(savedShipment);
    }

    @Override
    public ShipmentDTO getShipmentById(Long id) {
        return shipmentRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
    }

    @Override
    @Transactional
    public ShipmentDTO updateShipment(Long id, ShipmentDTO shipmentDTO) {
        Shipment existingShipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID가 " + id + "인 ShipmentDTO를 찾을 수 없습니다."));

        if (shipmentDTO.getShipNum() != null) {
            existingShipment.setShipNum(shipmentDTO.getShipNum());
        }
        if (shipmentDTO.getReceipt() != null) {
            existingShipment.setReceipt(shipmentDTO.getReceipt());
        }
        if (shipmentDTO.getInvoiceDTO() != null) {
            existingShipment.setInvoice(invoiceService.convertToEntity(shipmentDTO.getInvoiceDTO()));
        } else {
            existingShipment.setInvoice(null);
        }
        if (shipmentDTO.getBaljuDTO() != null) {
            existingShipment.setBalju(baljuService.convertToEntity(shipmentDTO.getBaljuDTO()));
        } else {
            existingShipment.setBalju(null);
        }
        if (shipmentDTO.getMemberDTO() != null) {
            existingShipment.setWriter(memberService.memberdtoToEntity(shipmentDTO.getMemberDTO()));
        }
        if (shipmentDTO.getReturnsDTO() != null) {
            Returns returns = returnsRepository.findById(shipmentDTO.getReturnsDTO().getReNO())
                    .orElseThrow(() -> new RuntimeException("Returns not found"));
            existingShipment.setReturns(returns);
        } else {
            existingShipment.setReturns(null);
        }

        Shipment updatedShipment = shipmentRepository.save(existingShipment);
        return convertToDTO(updatedShipment);
    }

    @Override
    @Transactional
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