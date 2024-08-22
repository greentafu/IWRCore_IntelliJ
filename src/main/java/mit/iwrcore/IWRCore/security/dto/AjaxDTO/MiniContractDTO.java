package mit.iwrcore.IWRCore.security.dto.AjaxDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class MiniContractDTO {
    private String joNo;
    private String inputCash;
    private String inputNum;
    private String inputDayNum;
    private String inputDate;
}
