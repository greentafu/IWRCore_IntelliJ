package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuChasuDTO;

@Builder
@Data
public class GumsuChasuContractDTO {
    private GumsuChasuDTO gumsuChasuDTO;
    private ContractDTO contractDTO;

    public GumsuChasuContractDTO(GumsuChasuDTO gumsuChasuDTO, ContractDTO contractDTO){
        this.gumsuChasuDTO=gumsuChasuDTO;
        this.contractDTO=contractDTO;
    }
}
