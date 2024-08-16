package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuChasuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;

import java.util.List;

@Builder
@Data
public class PartnerMainDTO {
    BaljuDTO baljuDTO;
    List<JodalChasuDTO> jodalChasuDTOs;
    List<GumsuChasuDTO> gumsuChasuDTOs;
}
