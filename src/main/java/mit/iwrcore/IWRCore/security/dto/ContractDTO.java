package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
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
    private Long partnerId;
}