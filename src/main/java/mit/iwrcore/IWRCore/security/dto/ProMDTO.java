package mit.iwrcore.IWRCore.security.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.ProL;

@Builder
@Data
@Log4j2
@Getter
@Setter
@ToString
public class ProMDTO {
    private Long proMcode;
    private String Mname;
    private ProL proL;

   
}
