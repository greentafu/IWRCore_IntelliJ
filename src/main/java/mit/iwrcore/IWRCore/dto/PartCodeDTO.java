package mit.iwrcore.IWRCore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartLDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartMDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartSDTO;

@Builder
@AllArgsConstructor
@Data
public class PartCodeDTO {
    private PartLDTO ldto;
    private PartMDTO mdto;
    private PartSDTO sdto;
}
