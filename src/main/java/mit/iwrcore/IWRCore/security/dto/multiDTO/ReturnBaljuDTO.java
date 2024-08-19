package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.ReturnsDTO;

import java.time.LocalDateTime;

@Builder
@Data
public class ReturnBaljuDTO {
    private ReturnsDTO returnsDTO;
    private Long shipNum;
    private LocalDateTime regDate;
    private BaljuDTO baljuDTO;

    public ReturnBaljuDTO(ReturnsDTO returnsDTO, Long shipNum, LocalDateTime regDate, BaljuDTO baljuDTO){
        this.returnsDTO=returnsDTO;
        this.shipNum=shipNum;
        this.regDate=regDate;
        this.baljuDTO=baljuDTO;
    }
}
