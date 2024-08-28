package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberServiceTests {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 직원 찾기
    @Test
    @Transactional
    @Commit
    public void findMember(){
//        System.out.println(memberService.findMember(1L, null));
//        System.out.println(memberService.findMember(null, "mate1_1234"));
//        System.out.println(memberService.findMemberEntity(1L, null));
//        System.out.println(memberService.findMemberDto(1L, null));
        System.out.println(memberRepository.findAll().size());
    }
    // 직원 리스트
    @Test
    @Transactional
    @Commit
    public void memberList(){
        PageRequestDTO pageRequestDTO=PageRequestDTO.builder().page(1).size(2).build();
        PageResultDTO<MemberDTO, Member> resultDTO=memberService.findMemberList(pageRequestDTO);
//        for(MemberDTO m:resultDTO.getDtoList()) System.out.println(m);
        System.out.println("#####"+resultDTO.isPrev()+"/"+resultDTO.isNext()+"/"+resultDTO.getTotalPage());
        System.out.println("=====================");
        resultDTO.getDtoList().forEach(System.out::println);
        System.out.println("=================");
        resultDTO.getPageList().forEach(System.out::println);
    }
    // 직원 삭제
    @Test
    @Transactional
    @Commit
    public void deleteMember(){
        memberService.deleteMember(44L);
    }
    // 직원 검색
    @Test
    @Transactional
    @Commit
    public void searchMember(){
        PageRequestDTO pageRequestDTO=PageRequestDTO.builder()
                .page(1)
                .size(2)
                .department(0)
                .role(0)
                .memberSearch("관리")
                .build();
        PageResultDTO<MemberDTO, Member> resultDTO=memberService.findMemberList(pageRequestDTO);
        for(MemberDTO memberDTO:resultDTO.getDtoList()){
            System.out.println(memberDTO);
        }
    }
}
