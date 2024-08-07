package mit.iwrcore.IWRCore.security.dto.MaterDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class MaterCodeListDTO {
    private List<MaterLDTO> materLDTOs;
    private List<MaterMDTO> materMDTOs;
    private List<MaterSDTO> materSDTOs;

    public Long l;
    public Long m;
    public Long s;
}
