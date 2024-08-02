package mit.iwrcore.IWRCore.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User {
    private long mno;
    private String id;
    private String name;

    public AuthMemberDTO(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
        this.id=username;
    }
}
