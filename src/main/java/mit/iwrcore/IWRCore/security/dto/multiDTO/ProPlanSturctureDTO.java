package mit.iwrcore.IWRCore.security.dto.multiDTO;

import lombok.Builder;
import lombok.Data;
import mit.iwrcore.IWRCore.entity.JodalChasu;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;

import java.util.List;

@Builder
@Data
public class ProPlanSturctureDTO {
    private ProplanDTO proplanDTO;
    private StructureDTO structureDTO;
    private Long sumRequest;
    private Long sumShip;
    private JodalPlanDTO jodalPlanDTO;
    private JodalChasuDTO jodalChasuDTO;
    private Long countContract;

    public ProPlanSturctureDTO(ProplanDTO proplanDTO, StructureDTO structureDTO, Long sumRequest, Long sumShip,
                               JodalPlanDTO jodalPlanDTO, JodalChasuDTO jodalChasuDTO, Long countContract){
        this.proplanDTO=proplanDTO;
        this.structureDTO=structureDTO;
        this.sumRequest=sumRequest;
        this.sumShip=sumShip;
        this.jodalPlanDTO=jodalPlanDTO;
        this.jodalChasuDTO=jodalChasuDTO;
        this.countContract=countContract;
    }
}
