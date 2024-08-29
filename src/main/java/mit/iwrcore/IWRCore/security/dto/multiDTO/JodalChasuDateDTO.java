package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;

import java.util.List;

@Builder
@Data
public class JodalChasuDateDTO {
    private Long jcnum;
    private Long juNum;
    private String joDate;

    public JodalChasuDateDTO(Long jcnum, Long juNum, String joDate){
        this.jcnum=jcnum;
        this.juNum=juNum;
        this.joDate=joDate;
    }
}
