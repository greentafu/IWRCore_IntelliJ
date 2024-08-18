package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.*;

import java.util.List;

@Builder
@Data
public class PartnerMainDTO {
    private BaljuDTO baljuDTO;
    private List<QuantityDateDTO> jodalList;
    private List<QuantityDateDTO> gumsuList;
    private Long emergency;
    private Long returns;
    private Long totalReturn;
    private Long totalShipment;
}
