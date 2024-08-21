package mit.iwrcore.IWRCore.security.dto.AjaxDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class SaveJodalChasuDTO {
    private String id;
    private String oneNum;
    private String twoNum;
    private String threeNum;
    private String oneDate;
    private String twoDate;
    private String threeDate;
}
