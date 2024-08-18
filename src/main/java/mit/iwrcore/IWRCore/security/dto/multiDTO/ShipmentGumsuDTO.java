package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.*;

@Builder
@Data
public class ShipmentGumsuDTO {
    private ShipmentDTO shipmentDTO;
    private GumsuDTO gumsuDTO;
    private Long totalShipment;

    public ShipmentGumsuDTO(ShipmentDTO shipmentDTO, GumsuDTO gumsuDTO, Long totalShipment){
        this.shipmentDTO=shipmentDTO;
        this.gumsuDTO=gumsuDTO;
        this.totalShipment=totalShipment;
    }
}
