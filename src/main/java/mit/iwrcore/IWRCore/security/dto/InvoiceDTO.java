package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class InvoiceDTO {
    private Long tranNO;                // 거래명세서 번호
    private Long plz;                  // 영수증 여부
    private LocalDateTime dateCreated; // 작성일
    private String filename;           // 파일

    private MemberDTO memberDTO;             // 작성자 ID
}
