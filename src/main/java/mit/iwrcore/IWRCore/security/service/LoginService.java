package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.Partner;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.repository.PartnerRepository;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthPartnerDTO;
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
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PartnerRepository partnerRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
        log.info("------------------------------------------------------------");
        log.info("ClubUserDetailsService get ID: "+id);

        Optional<Member> result1=memberRepository.findByID(id);
        Optional<Partner> result2=partnerRepository.findByID(id);

        if(result1.isEmpty()&&result2.isEmpty()){
            throw new UsernameNotFoundException("Check Email or Social ");
        }else if(result1.isPresent()){
            Member member=result1.get();
            log.info("------------------------------------------------------------");
            log.info("ClubUserDetailsService get member: "+member);

            AuthMemberDTO authMember=new AuthMemberDTO(
                    member.getId(),
                    member.getPassword(),
                    member.getRoleSet().stream()
                            .map(role->new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet())
            );
            authMember.setMno(member.getMno());
            authMember.setName(member.getName());
            authMember.setDepartment(member.getDepartment());

            return authMember;
        }else if(result2.isPresent()){
            Partner partner=result2.get();
            log.info("------------------------------------------------------------");
            log.info("ClubUserDetailsService get member: "+partner);

            AuthPartnerDTO authPartner=new AuthPartnerDTO(
                    partner.getId(),
                    partner.getPassword(),
                    partner.getRoleSet().stream()
                            .map(role->new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet())
            );
            authPartner.setPno(partner.getPno());
            authPartner.setName(partner.getName());

            return authPartner;
        }
        return null;
    }
}
