package mit.iwrcore.IWRCore.security.dto;

import jakarta.persistence.Entity;
import lombok.*;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.ProS;

import java.util.List;

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
    private String mater_imsi;
    private String mater_check;
    //외래키설정
    private ProS proS;
    private Material material;

}
