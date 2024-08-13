package mit.iwrcore.IWRCore.security.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class JpStDTO {
    private JodalPlanDTO jodalPlanDTO;
    private StructureDTO structureDTO;
}
