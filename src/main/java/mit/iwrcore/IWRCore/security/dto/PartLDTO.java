package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@ToString
@Builder
public class PartLDTO {
    private Long partLcode;
    private String Lname;
}