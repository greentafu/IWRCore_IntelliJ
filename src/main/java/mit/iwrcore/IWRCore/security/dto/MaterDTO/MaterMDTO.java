package mit.iwrcore.IWRCore.security.dto.MaterDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.MaterL;

@Log4j2
@Getter
@Setter
@ToString
@Builder
public class MaterMDTO {
    private Long materMcode;
    private String Mname;

    private MaterL materL;
}
