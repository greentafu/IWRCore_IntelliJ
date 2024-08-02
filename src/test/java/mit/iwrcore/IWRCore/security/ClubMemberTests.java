package mit.iwrcore.IWRCore.security;

import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.MemberRole;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ClubMemberTests {
    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies(){
        Member member1=Member.builder()
                .name("관리자")
                .password(passwordEncoder.encode("1111"))
                .phonenumber("000-0000-1234")
                .department("자재부서")
                .build();
        member1.changeMemberRole(MemberRole.MANAGER);
        repository.save(member1);

        Member member2=Member.builder()
                .name("자재팀")
                .password(passwordEncoder.encode("1111"))
                .phonenumber("000-0000-5678")
                .department("자재부서")
                .build();
        member2.changeMemberRole(MemberRole.MATERIAL_TEAM);
        repository.save(member2);

        Member member3=Member.builder()
                .name("생산부서")
                .password(passwordEncoder.encode("1111"))
                .phonenumber("000-0000-9012")
                .department("생산부서")
                .build();
        member3.changeMemberRole(MemberRole.DEV_PROD_TEAM);
        repository.save(member3);

        Member member4=Member.builder()
                .name("개발부서")
                .password(passwordEncoder.encode("1111"))
                .phonenumber("000-0000-3456")
                .department("개발부서")
                .build();
        member4.changeMemberRole(MemberRole.DEV_PROD_TEAM);
        repository.save(member4);
    }

    @Test
    public void testRead(){
        Optional<Member> result=repository.findByID("user1");
        Member member=result.get();
        System.out.println(member);
    }
}
