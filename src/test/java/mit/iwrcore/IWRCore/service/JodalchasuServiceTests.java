package mit.iwrcore.IWRCore.service;


import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.repository.JodalChasuRepository;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.service.JodalChasuService;
import mit.iwrcore.IWRCore.security.service.JodalPlanService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;

@SpringBootTest
public class JodalchasuServiceTests {
    @Autowired
    private JodalChasuRepository jodalChasuRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private JodalPlanService jodalPlanService;

    @Autowired
    private JodalChasuService jodalChasuService;


    @Transactional
    @Test
    @Commit
    public void insert(){
        JodalChasuDTO jodalChasuDTO = JodalChasuDTO.builder()
                .joDate(LocalDateTime.now().plusDays(3))
                .joNum(1L)
                .jodalPlanDTO(jodalPlanService.findById(1L))
                .memberDTO(memberService.findMemberDto(1L,null))
                .build();
        jodalChasuService.createJodalChasu(jodalChasuDTO);
    }

    @Test
    @Transactional
    @Commit
    public void test123(){
        JodalChasuDTO jodalChasuDTO=JodalChasuDTO.builder()
                .jodalPlanDTO(jodalPlanService.findById(1L))
                .joDate(LocalDateTime.now().plusDays(7L))
                .memberDTO(memberService.findMemberDto(1L, null))
                .joNum(700L)
                .build();
        jodalChasuService.createJodalChasu(jodalChasuDTO);
        JodalChasuDTO jodalChasuDTO1=JodalChasuDTO.builder()
                .jodalPlanDTO(jodalPlanService.findById(1L))
                .joDate(LocalDateTime.now().plusDays(7L))
                .memberDTO(memberService.findMemberDto(1L, null))
                .joNum(650L)
                .build();
        jodalChasuService.createJodalChasu(jodalChasuDTO1);
        JodalChasuDTO jodalChasuDTO2=JodalChasuDTO.builder()
                .jodalPlanDTO(jodalPlanService.findById(1L))
                .joDate(LocalDateTime.now().plusDays(7L))
                .memberDTO(memberService.findMemberDto(1L, null))
                .joNum(650L)
                .build();
        jodalChasuService.createJodalChasu(jodalChasuDTO2);
    }
    @Test
    @Transactional
    @Commit
    public void test11(){
        System.out.println(jodalChasuService.getJodalChasuById(1L));
    }
}
