package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.MemberRole;

import java.util.HashSet;
import java.util.Set;

@Log4j2
@Getter
@Setter
@ToString
@Builder
public class MemberDTO {
    private Long mno;
    private String name;
    private String department;
    private String phonenumber;
    private String id;
    private String pw;
    private String password;
    private Set<MemberRole> roleSet= new HashSet<>();
}
