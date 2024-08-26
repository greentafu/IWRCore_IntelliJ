package mit.iwrcore.IWRCore.security.dto;

import jakarta.persistence.Entity;
import lombok.*;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.ProS;
import mit.iwrcore.IWRCore.entity.Structure;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProSDTO;

import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ProductDTO {
    private Long manuCode;
    private String name;
    private String color;
    private String text;
    private String uuid;
    private String supervisor;
    private Long mater_imsi;
    private Long mater_check;
    //외래키설정
    private ProSDTO proSDTO;
    private MemberDTO memberDTO;
    private List<ProplanDTO> proPlans;
}
