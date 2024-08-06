package mit.iwrcore.IWRCore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.PartLDTO;
import mit.iwrcore.IWRCore.security.dto.PartMDTO;
import mit.iwrcore.IWRCore.security.dto.PartSDTO;

@Builder
@AllArgsConstructor
@Data
public class PartCodeDTO {
    private PartLDTO ldto;
    private PartMDTO mdto;
    private PartSDTO sdto;
}
