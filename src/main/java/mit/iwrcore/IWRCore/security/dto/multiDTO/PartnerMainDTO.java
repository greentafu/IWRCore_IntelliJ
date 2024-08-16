package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.*;

import java.util.List;

@Builder
@Data
public class PartnerMainDTO {
    BaljuDTO baljuDTO;
    List<JodalChasuDTO> jodalChasuDTOs;
    List<GumsuChasuDTO> gumsuChasuDTOs;
    List<EmergencyDTO> emergencyDTOs;
    List<ReturnsDTO> returnsDTOs;
}
