package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.ClubMember;
import mit.iwrcore.IWRCore.repository.ClubMemberRepository;
import mit.iwrcore.IWRCore.security.dto.ClubAuthMemberDTO;
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

    private final ClubMemberRepository clubMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        log.info("------------------------------------------------------------");
        log.info("ClubUserDetailsService loadUserByUsername "+username);

        Optional<ClubMember> result=clubMemberRepository.findByEmail(username, false);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check Email or Social ");
        }
        ClubMember clubMember=result.get();

        log.info("---------------------------");
        log.info(clubMember);
        log.info(clubMember.getEmail());
        log.info(clubMember.getPassword());
        log.info(clubMember.isFromSocial());
        log.info(clubMember.getRoleSet().stream()
                .map(role->new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet()));

        ClubAuthMemberDTO clubAuthMember=new ClubAuthMemberDTO(
                clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.isFromSocial(),
                clubMember.getRoleSet().stream()
                        .map(role->new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet())
        );
        clubAuthMember.setName(clubMember.getName());
        clubAuthMember.setFromSocial(clubMember.isFromSocial());

        return clubAuthMember;
    }
}
