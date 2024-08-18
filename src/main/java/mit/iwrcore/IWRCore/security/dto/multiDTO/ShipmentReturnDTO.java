package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.GumsuDTO;
import mit.iwrcore.IWRCore.security.dto.ShipmentDTO;

@Builder
@Data
public class ShipmentReturnDTO {
    private Long shipNo;
    private Long shipNum;
    private String materialName;

    public ShipmentReturnDTO(Long shipNo, Long shipNum, String materialName){
        this.shipNo=shipNo;
        this.shipNum=shipNum;
        this.materialName=materialName;
    }
}
