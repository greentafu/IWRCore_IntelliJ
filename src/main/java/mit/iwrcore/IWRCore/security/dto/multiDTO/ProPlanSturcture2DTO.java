package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;

import java.util.List;

@Builder
@Data
public class ProPlanSturcture2DTO {
    private ProplanDTO proplanDTO;
    private StructureDTO structureDTO;
    private Long sumRequest;
    private Long sumShip;
    private JodalPlanDTO jodalPlanDTO;
    private List<JodalChasuDateDTO> jodalChasuDateDTOList;
    private Long countContract;

    public ProPlanSturcture2DTO(ProplanDTO proplanDTO, StructureDTO structureDTO, Long sumRequest, Long sumShip,
                                JodalPlanDTO jodalPlanDTO, List<JodalChasuDateDTO> jodalChasuDateDTOList, Long countContract){
        this.proplanDTO=proplanDTO;
        this.structureDTO=structureDTO;
        this.sumRequest=sumRequest;
        this.sumShip=sumShip;
        this.jodalPlanDTO=jodalPlanDTO;
        this.jodalChasuDateDTOList=jodalChasuDateDTOList;
        this.countContract=countContract;
    }
}
