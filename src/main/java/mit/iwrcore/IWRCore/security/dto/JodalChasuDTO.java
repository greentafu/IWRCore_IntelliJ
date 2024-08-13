package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class JodalChasuDTO {
    private Long joNum;
    private LocalDateTime joDate;


    private MemberDTO memberDTO;
    private JodalPlanDTO jodalPlanDTO;
}
