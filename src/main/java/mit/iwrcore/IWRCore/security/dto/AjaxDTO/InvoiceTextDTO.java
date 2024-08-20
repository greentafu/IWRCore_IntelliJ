package mit.iwrcore.IWRCore.security.dto.AjaxDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class InvoiceTextDTO {
    private Long shipNo;
    private String shipText;
}
