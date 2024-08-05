package mit.iwrcore.IWRCore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.ProLDTO;
import mit.iwrcore.IWRCore.security.dto.ProMDTO;
import mit.iwrcore.IWRCore.security.dto.ProSDTO;


@Builder
@AllArgsConstructor
@Data
public class ProCodeDTO {
    private ProLDTO ldto;
    private ProMDTO mdto;
    private ProSDTO sdto;
}
