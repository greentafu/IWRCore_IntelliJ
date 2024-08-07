package mit.iwrcore.IWRCore.security.dto.PartDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
