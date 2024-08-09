package mit.iwrcore.IWRCore.security.dto;


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
    private MaterialDTO materialDTO;   // 자재 ID
    private ProductDTO productDTO;    // 제품 ID
    private Long quantity;     // 수량
}
