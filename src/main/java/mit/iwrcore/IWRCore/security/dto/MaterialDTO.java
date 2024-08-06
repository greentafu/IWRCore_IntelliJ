package mit.iwrcore.IWRCore.security.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

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
    private String Standard;    //규격
    private String color;       //색상
    private String file;        //파일
    private LocalDateTime date;        //등록일자

}
