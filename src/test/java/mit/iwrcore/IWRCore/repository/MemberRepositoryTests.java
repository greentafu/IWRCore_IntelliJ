package mit.iwrcore.IWRCore.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.MemberRole;
import mit.iwrcore.IWRCore.entity.QMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;


@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    @Commit
    public void insert(){
        Member member1 = Member.builder()
                .name("테스트1")
                .pw("1111")
                .password(passwordEncoder.encode("1111"))
                .phonenumber("000-0000-1234")
                .department("자재부서")
                .build();
        member1.changeMemberRole(MemberRole.MANAGER);
        memberRepository.save(member1);

        Member member2 = Member.builder()
                .name("테스트2")
                .pw("1111")
                .password(passwordEncoder.encode("1111"))
                .phonenumber("000-0000-5678")
                .department("자재부서")
                .build();
        member2.changeMemberRole(MemberRole.MATERIAL_TEAM);
        memberRepository.save(member2);

        Member member3 = Member.builder()
                .name("테스트3")
                .pw("1111")
                .password(passwordEncoder.encode("1111"))
                .phonenumber("000-0000-9012")
                .department("생산부서")
                .build();
        member3.changeMemberRole(MemberRole.DEV_PROD_TEAM);
        memberRepository.save(member3);

        Member member4 = Member.builder()
                .name("테스트4")
                .pw("1111")
                .password(passwordEncoder.encode("1111"))
                .phonenumber("000-0000-3456")
                .department("개발부서")
                .build();
        member4.changeMemberRole(MemberRole.DEV_PROD_TEAM);
        memberRepository.save(member4);
    }

    @Test
    @Transactional
    @Commit
    public void dslTest(){
        Pageable pageable= PageRequest.of(0,2, Sort.by("mno"));
        QMember qMember=QMember.member;
        String keyword="3";
        String keyword2="개발";

        BooleanBuilder builder=new BooleanBuilder();

        BooleanExpression expression=qMember.phonenumber.contains(keyword);
        BooleanExpression expression1=qMember.department.contains(keyword2);

        BooleanExpression all=expression.and(expression1);

        builder.and(all);

        Page<Member> result=memberRepository.findAll(builder, pageable);

        result.stream().forEach(System.out::println);
    }

}
