package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BaljuDTO {
    private Long baljuNo; // 발주서 번호
    private Long baljuNum; // 수량
    private LocalDateTime baljuDate; // 요청일
    private String baljuWhere; // 배송장소
    private String baljuPlz; // 요청사항
    private String filename; // 파일
    private Long finCheck;
    private LocalDateTime regDate;


    private MemberDTO memberDTO; // 작성자 ID
    private ContractDTO contractDTO; // 계약 ID
}
