package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GumsuDTO {
    private Long gumsuNo;
    private Long make;
    private String who;
    private LocalDateTime gumsuDate;


    private BaljuDTO baljuDTO;// 연관된 Balju의 ID
    private MemberDTO memberDTO;
}
