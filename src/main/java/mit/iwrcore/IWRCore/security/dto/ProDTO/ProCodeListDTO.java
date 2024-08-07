package mit.iwrcore.IWRCore.security.dto.ProDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class ProCodeListDTO {
    List<ProLDTO> proLDTOs;
    List<ProMDTO> proMDTOs;
    List<ProSDTO> proSDTOs;

    public Long l;
    public Long m;
    public Long s;
}
