package mit.iwrcore.IWRCore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProLDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProMDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProSDTO;

@Builder
@AllArgsConstructor
@Data
public class ProCodeDTO {
    private ProLDTO ldto;
    private ProMDTO mdto;
    private ProSDTO sdto;
}
