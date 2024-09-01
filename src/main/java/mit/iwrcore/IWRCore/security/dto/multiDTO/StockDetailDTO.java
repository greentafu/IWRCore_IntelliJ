package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.ReturnsDTO;

import java.time.LocalDateTime;

@Builder
@Data
public class StockDetailDTO {
    private ContractDTO contractDTO;
    private Long sumShipNum;
    private Long sumRequestNum;

    public StockDetailDTO(ContractDTO contractDTO, Long sumShipNum, Long sumRequestNum){
        this.contractDTO=contractDTO;
        this.sumShipNum=sumShipNum;
        this.sumRequestNum=sumRequestNum;
    }
}
