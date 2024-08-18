package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;

@Builder
@Data
public class ContractBaljuDTO {
    private ContractDTO contractDTO;
    private BaljuDTO baljuDTO;

    public ContractBaljuDTO(ContractDTO contractDTO, BaljuDTO baljuDTO){
        this.contractDTO=contractDTO;
        this.baljuDTO=baljuDTO;
    }
}
