package mit.iwrcore.IWRCore.security.dto.AjaxDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.GumsuChasuDTO;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class NoneGumsuChasuDTO {
    private List<NoneGumsuDTO> result;
    private List<GumsuChasuDTO> gumsuChasuDTOs;
}
