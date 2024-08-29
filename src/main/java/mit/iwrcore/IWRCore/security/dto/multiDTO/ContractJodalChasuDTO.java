package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;

@Builder
@Data
public class ContractJodalChasuDTO {
    private JodalPlanDTO jodalPlanDTO;
    private ContractDTO contractDTO;
    private JodalChasuDTO jodalChasuDTO;
    private Long allNum;

    public ContractJodalChasuDTO(JodalPlanDTO jodalPlanDTO, ContractDTO contractDTO, JodalChasuDTO jodalChasuDTO, Long allNum){
        this.jodalPlanDTO=jodalPlanDTO;
        this.contractDTO=contractDTO;
        this.jodalChasuDTO=jodalChasuDTO;
        this.allNum=allNum;
    }
}
