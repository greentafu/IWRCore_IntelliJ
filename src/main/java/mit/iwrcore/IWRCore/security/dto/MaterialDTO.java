package mit.iwrcore.IWRCore.security.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Box;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterMDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;

import java.io.Writer;
import java.time.LocalDateTime;

@Log4j2
@Getter
@Setter
@ToString
@Builder
public class MaterialDTO {
    private Long materCode;     //코드
    private String name;        //이름
    private String unit;        //단위
    private String standard;    //규격
    private String color;       //색상
    private LocalDateTime date;        //등록일자

    private String file;

    private BoxDTO boxDTO;
    private MaterSDTO materSDTO;
    private MemberDTO memberDTO;


}
