package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.security.dto.AuthMemberDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService {

    private final MemberRepository MemberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
        log.info("------------------------------------------------------------");
        log.info("ClubUserDetailsService loadUserByUsername "+id);

        Optional<Member> result=MemberRepository.findByID(id);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check Email or Social ");
        }
        Member member=result.get();

        log.info("---------------------------");
        log.info(member);

        AuthMemberDTO authMember=new AuthMemberDTO(
                member.getId(),
                member.getPassword(),
                member.getRoleSet().stream()
                        .map(role->new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet())
        );
        authMember.setName(member.getName());

        return authMember;
    }
}
