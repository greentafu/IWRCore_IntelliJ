package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShipmentDTO {
    private Long shipNO;               // 출고 번호
    private Long shipNum;              // 출고 수량
    private LocalDateTime receipt;     // 입고 일
    private String text;
    private Long receiveCheck;

    private ReturnsDTO returnsDTO;            // 관련된 Returns ID
    private InvoiceDTO invoiceDTO;            // 관련된 Invoice ID
    private BaljuDTO baljuDTO;              // 관련된 Balju I
    private MemberDTO memberDTO;
}
