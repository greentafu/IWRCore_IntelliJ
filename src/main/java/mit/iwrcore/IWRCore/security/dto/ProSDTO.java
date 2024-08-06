package mit.iwrcore.IWRCore.security.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.ProM;

@Builder
@Data
@Log4j2
@Getter
@Setter
@ToString
public class ProSDTO {
    private Long proScode;
    private String Sname;
    private ProM proM;
}
