package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuChasuDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;

@Builder
@Data
public class StockDTO {
    private MaterialDTO materialDTO;
    private Long money;
    private Long sumShipNum;
    private Long sumRequestNum;

    public StockDTO(MaterialDTO materialDTO, Long money, Long sumShipNum, Long sumRequestNum){
        this.materialDTO=materialDTO;
        this.money=money;
        this.sumShipNum=sumShipNum;
        this.sumRequestNum=sumRequestNum;
    }
}
