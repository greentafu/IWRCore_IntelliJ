package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;

@Builder
@Data
public class ProPlanContractNumDTO {
    private ProplanDTO proplanDTO;
    private Long jcNum;
    private Long contractNum;

    public ProPlanContractNumDTO(ProplanDTO proplanDTO, Long jcNum, Long contractNum){
        this.proplanDTO=proplanDTO;
        this.jcNum=jcNum;
        this.contractNum=contractNum;
    }
}
