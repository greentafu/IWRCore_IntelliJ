package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GumsuChasuDTO {
    private Long gcnum;
    private Long gumsuNum;
    private LocalDateTime gumsu1;

    private MemberDTO memberDTO; // 연관된 Member의 ID
    private GumsuDTO gumsuDTO;  // 연관된 Gumsu의 ID
}
