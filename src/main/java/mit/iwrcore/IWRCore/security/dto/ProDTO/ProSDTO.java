package mit.iwrcore.IWRCore.security.dto.ProDTO;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Builder
@Data
@Log4j2
@Getter
@Setter
@ToString
public class ProSDTO {
    private Long proScode;
    private String Sname;
    private ProMDTO proMDTO;
}
