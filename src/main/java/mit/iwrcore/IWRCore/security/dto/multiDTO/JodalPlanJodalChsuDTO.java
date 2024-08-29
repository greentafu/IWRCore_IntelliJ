package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;

@Builder
@Data
public class JodalPlanJodalChsuDTO {
    private JodalPlanDTO jodalPlanDTO;
    private Long allJodalChasuNum;

    public JodalPlanJodalChsuDTO(JodalPlanDTO jodalPlanDTO, Long allJodalChasuNum){
        this.jodalPlanDTO=jodalPlanDTO;
        this.allJodalChasuNum=allJodalChasuNum;
    }
}
