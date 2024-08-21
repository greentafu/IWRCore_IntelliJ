package mit.iwrcore.IWRCore.security.dto.AjaxDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class SaveBaljuDTO {
    private String conNo;
    private String baljuWhere;
    private String baljuPlz;
    private List<MiniJodalChasuDTO> chasuList;
}
