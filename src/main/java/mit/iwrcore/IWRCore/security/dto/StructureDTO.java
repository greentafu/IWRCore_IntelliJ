package mit.iwrcore.IWRCore.security.dto;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@ToString
@Builder
public class StructureDTO {
    private Long sno;           // 교차 테이블 ID
    private Long materialId;   // 자재 ID
    private Long productId;    // 제품 ID
    private Long quantity;     // 수량
}
