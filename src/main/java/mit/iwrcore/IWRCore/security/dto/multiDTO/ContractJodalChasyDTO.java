package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;

@Builder
@Data
public class ContractJodalChasyDTO {
    private JodalPlanDTO jodalPlanDTO;
    private ContractDTO contractDTO;
    private JodalChasuDTO jodalChasuDTO;

    public ContractJodalChasyDTO(JodalPlanDTO jodalPlanDTO, ContractDTO contractDTO, JodalChasuDTO jodalChasuDTO){
        this.jodalPlanDTO=jodalPlanDTO;
        this.contractDTO=contractDTO;
        this.jodalChasuDTO=jodalChasuDTO;
    }
}
