package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuDTO;

@Builder
@Data
public class BaljuGumsuDTO {
    private BaljuDTO baljuDTO;
    private GumsuDTO gumsuDTO;

    public BaljuGumsuDTO(BaljuDTO baljuDTO, GumsuDTO gumsuDTO){
        this.baljuDTO=baljuDTO;
        this.gumsuDTO=gumsuDTO;
    }
}
