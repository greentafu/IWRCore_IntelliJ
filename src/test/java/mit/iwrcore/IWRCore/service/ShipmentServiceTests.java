package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Invoice;
import mit.iwrcore.IWRCore.entity.Returns;
import mit.iwrcore.IWRCore.entity.Shipment;
import mit.iwrcore.IWRCore.repository.InvoiceRepository;
import mit.iwrcore.IWRCore.repository.ReturnsRepository;
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
    @Autowired
    private ReturnsRepository returnsRepository;
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
        }}
        @Test
        @Transactional
        @Commit
        public void linkShipmentToReturns() {
            // 1단계: 기존에 데이터베이스에 저장된 Shipment과 Returns의 ID를 정의합니다.
            Long shipmentId = 1L; // 테스트에 사용할 기존 Shipment ID
            Long returnsId = 1L;  // 테스트에 사용할 기존 Returns ID

            // 2단계: Shipment과 Returns를 연결합니다.
            shipmentService.updateShipmentWithReturns(shipmentId, returnsId);

            // 3단계: 연결 결과를 확인하기 위해 Shipment과 Returns를 데이터베이스에서 조회합니다.
            Shipment updatedShipment = shipmentRepository.findById(shipmentId)
                    .orElseThrow(() -> new RuntimeException("데이터베이스에서 Shipment을 찾을 수 없습니다."));
            Returns linkedReturns = returnsRepository.findById(returnsId)
                    .orElseThrow(() -> new RuntimeException("데이터베이스에서 Returns를 찾을 수 없습니다."));

            // 4단계: 연결된 결과를 콘솔에 출력합니다. 이 정보를 기반으로 DB에서 수동으로 확인할 수 있습니다.
            System.out.println("업데이트된 Shipment: " + updatedShipment);
            System.out.println("연결된 Returns: " + linkedReturns);

            // 5단계: 테스트를 수동으로 검증하기 위해 연결 상태를 확인합니다.
            // Shipment의 returns 필드가 올바르게 설정되었는지 확인합니다.
            if (updatedShipment.getReturns() == null) {
                throw new RuntimeException("Shipment의 Returns가 연결되지 않았습니다.");
            }

            // Returns의 shipment 필드가 올바르게 설정되었는지 확인합니다.
            if (linkedReturns.getShipment() == null) {
                throw new RuntimeException("Returns의 Shipment가 연결되지 않았습니다.");
            }
        }
    }



