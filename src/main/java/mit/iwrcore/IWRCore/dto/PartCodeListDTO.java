package mit.iwrcore.IWRCore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.PartLDTO;
import mit.iwrcore.IWRCore.security.dto.PartMDTO;
import mit.iwrcore.IWRCore.security.dto.PartSDTO;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class PartCodeListDTO {
    private List<PartLDTO> partLDTOs;
    private List<PartMDTO> partMDTOs;
    private List<PartSDTO> partSDTOs;

    public Long l;
    public Long m;
    public Long s;
}
