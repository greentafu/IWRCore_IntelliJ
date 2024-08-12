package mit.iwrcore.IWRCore.security.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ReturnsDTO {
    private Long reNO;           // 반품 번호
    private String reDetail;    // 반품 내용
    private String whyRe;       // 반품 이유
    private String bGo;         // 비고
    private String filename;    // 파일
    private String email;       // 담당자 이메일

    private ShipmentDTO shipmentDTO;    // 관련된 Shipment ID
    private MemberDTO memberDTO;      // 작성자 ID
}
