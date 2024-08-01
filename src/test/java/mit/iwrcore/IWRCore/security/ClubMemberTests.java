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
        //1-80까지는 USER만 지정
        //81-90까지는 USER, MANAGER
        //91-100까지는 USER, MANAGER, ADMIN
        IntStream.rangeClosed(1, 100).forEach(i->{
            Member clubMember= Member.builder()
                    .name("사용자"+i)
                    .password(passwordEncoder.encode("1111"))
                    .build();
            //default role
//            clubMember.addMemberRole(MemberRole.USER);
//            if(i>80){
//                clubMember.addMemberRole(MemberRole.MANAGER);
//            }
//            if(i>90){
//                clubMember.addMemberRole(MemberRole.ADMIN);
//            }
//            repository.save(clubMember);
        });
    }

//    @Test
//    public void testRead(){
//        Optional<Member> result=repository.findByEmail("user95@zerock.org", false);
//        Member clubMember=result.get();
//        System.out.println(clubMember);
//    }
}
