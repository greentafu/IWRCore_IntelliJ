package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.entity.Balju;

import java.time.LocalDateTime;

@Data
@Builder
public class EmergencyDTO {
    private Long emerNo;
    private Long emerNum;
    private LocalDateTime emerDate;
    private String who;
    private Long emcheck;

    private BaljuDTO baljuDTO;
    private MemberDTO memberDTO;
    private RequestDTO requestDTO;

}
