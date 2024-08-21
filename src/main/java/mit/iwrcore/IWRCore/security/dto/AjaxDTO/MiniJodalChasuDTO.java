package mit.iwrcore.IWRCore.security.dto.AjaxDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class MiniJodalChasuDTO {
    private String jodalNo;
    private String JodlaNum;
    private String JodlaDate;
}
