package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.InvoiceDTO;
import mit.iwrcore.IWRCore.security.dto.PartnerDTO;

@Builder
@Data
public class InvoicePartnerDTO {
    private InvoiceDTO invoiceDTO;
    private PartnerDTO partnerDTO;

    public InvoicePartnerDTO(InvoiceDTO invoiceDTO, PartnerDTO partnerDTO){
        this.invoiceDTO=invoiceDTO;
        this.partnerDTO=partnerDTO;
    }
}
