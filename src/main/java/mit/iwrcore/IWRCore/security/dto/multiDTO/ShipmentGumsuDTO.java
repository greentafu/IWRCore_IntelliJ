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
    private Long reNo;

    public ShipmentGumsuDTO(ShipmentDTO shipmentDTO, GumsuDTO gumsuDTO, Long totalShipment, Long reNo){
        this.shipmentDTO=shipmentDTO;
        this.gumsuDTO=gumsuDTO;
        this.totalShipment=totalShipment;
        this.reNo=reNo;
    }
}
