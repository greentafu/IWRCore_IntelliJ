package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuChasuDTO;

import java.time.LocalDateTime;

@Builder
@Data
public class GumsuChasuContractDTO {
    private GumsuChasuDTO gumsuChasuDTO;
    private ContractDTO contractDTO;
    private Long allShipNum;
    private Long remainingDate;
    private Double percent;

    public GumsuChasuContractDTO(GumsuChasuDTO gumsuChasuDTO, ContractDTO contractDTO, Long allShipNum, Long remainingDate, Double percent){
        this.gumsuChasuDTO=gumsuChasuDTO;
        this.contractDTO=contractDTO;
        this.allShipNum=allShipNum;
        this.remainingDate=remainingDate;
        this.percent=percent;
    }
}
