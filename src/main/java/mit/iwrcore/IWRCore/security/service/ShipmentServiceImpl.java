package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.*;
import mit.iwrcore.IWRCore.security.dto.ShipmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final InvoiceService invoiceService;
    private final BaljuService baljuService;
    private final MemberService memberService;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository shipmentRepository,
                               InvoiceService invoiceService,
                               BaljuService baljuService,
                               MemberService memberService,
                               InvoiceRepository invoiceRepository) {
        this.shipmentRepository = shipmentRepository;
        this.invoiceService = invoiceService;
        this.baljuService = baljuService;
        this.memberService = memberService;
        this.invoiceRepository = invoiceRepository;
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
        return Shipment.builder()
                .shipNO(dto.getShipNO())
                .shipNum(dto.getShipNum())
                .receipt(dto.getReceipt())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO())) // MemberDTO를 Member로 변환
                .returns(dto.getReturnsId())
                .invoice(dto.getInvoiceDTO() != null ? invoiceService.convertToEntity(dto.getInvoiceDTO()) : null) // InvoiceDTO를 Invoice로 변환
                .balju(dto.getBaljuDTO() != null ? baljuService.convertToEntity(dto.getBaljuDTO()) : null) // BaljuDTO를 Balju로 변환
                .build();
    }

    @Override
    public ShipmentDTO convertToDTO(Shipment entity) {
        return ShipmentDTO.builder()
                .shipNO(entity.getShipNO())
                .shipNum(entity.getShipNum())
                .receipt(entity.getReceipt())
                .returnsId(entity.getReturns()) // Returns를 ID로 변환
                .invoiceDTO(entity.getInvoice() != null ? invoiceService.convertToDTO(entity.getInvoice()) : null) // Invoice를 InvoiceDTO로 변환
                .baljuDTO(entity.getBalju() != null ? baljuService.convertToDTO(entity.getBalju()) : null) // Balju를 BaljuDTO로 변환
                .memberDTO(memberService.memberTodto(entity.getWriter())) // Member를 MemberDTO로 변환
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
        return convertToDTO(shipmentRepository.findById(id).get());
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
        if (shipmentDTO.getReturnsId() != null) {
            existingShipment.setReturns(shipmentDTO.getReturnsId());
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