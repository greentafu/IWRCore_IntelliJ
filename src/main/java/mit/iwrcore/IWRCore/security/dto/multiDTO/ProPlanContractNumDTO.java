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
}
