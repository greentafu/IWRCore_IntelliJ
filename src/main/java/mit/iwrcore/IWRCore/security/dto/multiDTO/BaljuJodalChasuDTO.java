package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;

@Builder
@Data
public class BaljuJodalChasuDTO {
    private BaljuDTO baljuDTO;
    private JodalChasuDTO jodalChasuDTO;

    public BaljuJodalChasuDTO(BaljuDTO baljuDTO, JodalChasuDTO jodalChasuDTO){
        this.baljuDTO=baljuDTO;
        this.jodalChasuDTO=jodalChasuDTO;
    }
}
