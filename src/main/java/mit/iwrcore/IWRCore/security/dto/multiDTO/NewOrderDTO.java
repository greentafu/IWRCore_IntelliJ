package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;

import java.util.List;

@Builder
@Data
public class NewOrderDTO {
    private ContractDTO contractDTO;
    private List<JodalChasuDTO> jodalChasuDTOs;
    private BaljuDTO baljuDTO;

    public NewOrderDTO(ContractDTO contractDTO, List<JodalChasuDTO> jodalChasuDTOs, BaljuDTO baljuDTO){
        this.contractDTO=contractDTO;
        this.jodalChasuDTOs=jodalChasuDTOs;
        this.baljuDTO=baljuDTO;
    }
}
