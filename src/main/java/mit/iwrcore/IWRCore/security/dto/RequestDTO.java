package mit.iwrcore.IWRCore.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class RequestDTO {
    private Long requestCode;
    private Long requestNum;
    private LocalDate eventDate;
    private String text;
    private Long reqCheck;
    private String line;

    private MaterialDTO materialDTO;
    private ProplanDTO proplanDTO;
    private MemberDTO memberDTO;
}
