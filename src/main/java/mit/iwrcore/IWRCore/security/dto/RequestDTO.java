package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RequestDTO {
    private Long requstCode;
    private LocalDate eventDate;
    private String text;
    private Long reqCheck;
    private String line;

    private MaterialDTO materialDTO;
    private ProplanDTO proplanDTO;
    private MemberDTO memberDTO;




}
