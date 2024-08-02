package mit.iwrcore.IWRCore.security;

import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.MemberRole;
import mit.iwrcore.IWRCore.entity.Partner;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.repository.PartnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootTest
public class MemberPartnerTests {
    @Autowired
    private MemberRepository repository;
    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies(){
        Member member1=Member.builder()
                .name("관리자")
                .pw("1111")
                .password(passwordEncoder.encode("1111"))
                .phonenumber("000-0000-1234")
                .department("자재부서")
                .build();
        member1.changeMemberRole(MemberRole.MANAGER);
        repository.save(member1);

        Member member2=Member.builder()
                .name("자재팀")
                .pw("1111")
                .password(passwordEncoder.encode("1111"))
                .phonenumber("000-0000-5678")
                .department("자재부서")
                .build();
        member2.changeMemberRole(MemberRole.MATERIAL_TEAM);
        repository.save(member2);

        Member member3=Member.builder()
                .name("생산부서")
                .pw("1111")
                .password(passwordEncoder.encode("1111"))
                .phonenumber("000-0000-9012")
                .department("생산부서")
                .build();
        member3.changeMemberRole(MemberRole.DEV_PROD_TEAM);
        repository.save(member3);

        Member member4=Member.builder()
                .name("개발부서")
                .pw("1111")
                .password(passwordEncoder.encode("1111"))
                .phonenumber("000-0000-3456")
                .department("개발부서")
                .build();
        member4.changeMemberRole(MemberRole.DEV_PROD_TEAM);
        repository.save(member4);
    }

    @Test
    public void insertPartner(){
        Partner partner1=Partner.builder()
                .name("협력회사1")
                .registrationNumber("123-45-67890")
                .location("경기도 수원시")
                .type("제조업")
                .sector("금속사출부품")
                .ceo("대표자1")
                .telNumber("000-0000-0000")
                .faxNumber("000-0000-0001")
                .email("partner1@mail.com")
                .contacter("담당자1")
                .contacterNumber("000-1111-1111")
                .contacterEmail("contacter1@mail.com")
                .pw("1111")
                .password(passwordEncoder.encode("1111"))
                .build();
        partner1.setPartnerRole(MemberRole.PARTNER);
        partnerRepository.save(partner1);
    }

    @Test
    public void testRead(){
        Optional<Member> result=repository.findByID("mate1_1234");
        Member member=result.get();
        System.out.println(member);
    }
}