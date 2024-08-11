package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Getter
@Setter
@ToString
@Builder
public class PlanDTO {
    private Long plancode;
    private ProductDTO productDTO;  // 연관된 Product의 ID 목록
    private Long quantity;
    private String line;

}
