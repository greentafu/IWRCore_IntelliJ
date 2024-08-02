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
                .id("user1")
                .password("1111")
                .phonenumber("000-0000-0000")
                .department("자재부서")
                .build();
        member1.changeMemberRole(MemberRole.MANAGER);
        repository.save(member1);

        Member member2=Member.builder()
                .name("자재팀")
                .id("user2")
                .password("1111")
                .phonenumber("000-0000-0000")
                .department("자재부서")
                .build();
        member2.changeMemberRole(MemberRole.MATERIAL_TEAM);
        repository.save(member2);

        Member member3=Member.builder()
                .name("자재팀")
                .id("user3")
                .password("1111")
                .phonenumber("000-0000-0000")
                .department("자재부서")
                .build();
        member3.changeMemberRole(MemberRole.DEV_PROD_TEAM);
        repository.save(member3);
    }

//    @Test
//    public void testRead(){
//        Optional<Member> result=repository.findByEmail("user95@zerock.org", false);
//        Member clubMember=result.get();
//        System.out.println(clubMember);
//    }
}
