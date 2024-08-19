package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.InvoiceDTO;
import mit.iwrcore.IWRCore.security.dto.ReturnsDTO;
import mit.iwrcore.IWRCore.security.dto.ShipmentDTO;

@Builder
@Data
public class ShipmentReturn2DTO {
    private ShipmentDTO shipmentDTO;
    private Long reNo;

    public ShipmentReturn2DTO(ShipmentDTO shipmentDTO, Long reNo){
        this.shipmentDTO=shipmentDTO;
        this.reNo=reNo;
    }
}
