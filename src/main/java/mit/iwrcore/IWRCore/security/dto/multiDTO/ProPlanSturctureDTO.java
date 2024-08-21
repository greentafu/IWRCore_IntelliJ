package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;

@Builder
@Data
public class ProPlanSturctureDTO {
    private ProplanDTO proplanDTO;
    private StructureDTO structureDTO;
    private Long sumRequest;
    private Long sumShip;
    private JodalPlanDTO jodalPlanDTO;

    public ProPlanSturctureDTO(ProplanDTO proplanDTO, StructureDTO structureDTO, Long sumRequest, Long sumShip, JodalPlanDTO jodalPlanDTO){
        this.proplanDTO=proplanDTO;
        this.structureDTO=structureDTO;
        this.sumRequest=sumRequest;
        this.sumShip=sumShip;
        this.jodalPlanDTO=jodalPlanDTO;
    }
}
