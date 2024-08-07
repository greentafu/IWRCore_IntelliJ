package mit.iwrcore.IWRCore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProLDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProMDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProSDTO;

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
