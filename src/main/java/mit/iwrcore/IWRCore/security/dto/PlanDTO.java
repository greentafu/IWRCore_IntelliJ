package mit.iwrcore.IWRCore.security.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Data
@ToString
@Builder
@AllArgsConstructor
public class PlanDTO {
    private Long plancode;
    private ProductDTO productDTO;  // 연관된 Product의 ID 목록
    private Long quantity;
    private String line;

}
