package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class QuantityDateDTO {
    private Long quantity;
    private LocalDateTime dueDate;
    private Float percent;
    private Float count;
    private Long totalOrder;
}
