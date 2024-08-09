package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ContractDTO {
    private Long conNo;
    private Long conNum;
    private Long money;
    private Long howDate;
    private LocalDateTime conDate;
    private String filename;
    private String who;
    private Long writerId;
}