package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Invoice;
import mit.iwrcore.IWRCore.entity.Shipment;
import mit.iwrcore.IWRCore.repository.InvoiceRepository;
import mit.iwrcore.IWRCore.repository.ShipmentRepository;
import mit.iwrcore.IWRCore.security.dto.InvoiceDTO;
import mit.iwrcore.IWRCore.security.dto.ShipmentDTO;
import mit.iwrcore.IWRCore.security.service.BaljuService;
import mit.iwrcore.IWRCore.security.service.InvoiceService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import mit.iwrcore.IWRCore.security.service.ShipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;

@SpringBootTest
public class ShipmentServiceTests {
    @Autowired
    private BaljuService baljuService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Test
    @Transactional
    @Commit
    public void insert(){
        ShipmentDTO dto= ShipmentDTO.builder()
                .shipNum(10L)
                .receipt(LocalDateTime.of(2024,8,30,10,20))
                .memberDTO(memberService.findMemberDto(1L,null))
                .invoiceDTO(null)
                .baljuDTO(baljuService.getBaljuById(1L))
                .build();
        shipmentService.createShipment(dto);
    }
    @Test
    @Transactional
    @Commit
    public void link(){
        Long shipmentId = 1L; // 이미 존재하는 Shipment ID
        Long invoiceId = 1L; // 이미 존재하는 Invoice ID

        // Link the existing Invoice to the Shipment
        ShipmentDTO updatedShipmentDTO = shipmentService.linkShipmentToInvoice(shipmentId, invoiceId);
        System.out.println("Updated ShipmentDTO: " + updatedShipmentDTO);

        // Verify the Shipment is linked to the correct Invoice
        Shipment savedShipment = shipmentRepository.findById(shipmentId).orElse(null);
        if (savedShipment == null) {
            throw new RuntimeException("Saved Shipment not found in the database");
        }

        Invoice invoice = invoiceRepository.findById(invoiceId).orElse(null);
        if (invoice == null) {
            throw new RuntimeException("Invoice not found in the database");
        }

        if (!invoice.equals(savedShipment.getInvoice())) {
            throw new RuntimeException("The Invoice linked to the Shipment is incorrect");
        }
    }
    }


