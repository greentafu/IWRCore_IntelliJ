package mit.iwrcore.IWRCore.security.dto.AjaxDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class SaveContractDTO {
    private String person;
    private String partnerNo;
    private String file;
    private List<MiniContractDTO> planData;
}
