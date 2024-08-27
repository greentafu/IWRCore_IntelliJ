package mit.iwrcore.IWRCore.security.dto.AjaxDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class SaveGumsuDTO {
    String baljuNo;
    String person;
    String partnerNo;
    List<TdData> tdData;
}
