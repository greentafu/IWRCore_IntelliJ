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
}
