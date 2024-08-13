package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.GumsuChasu;
import mit.iwrcore.IWRCore.security.dto.GumsuChasuDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuDTO;
import mit.iwrcore.IWRCore.security.service.GumsuChasuService;
import mit.iwrcore.IWRCore.security.service.GumsuService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;

@SpringBootTest
public class GumsuChasuTests {
    @Autowired
    private MemberService memberService;
    @Autowired
    private GumsuService gumsuService;
    @Autowired
    private GumsuChasuService gumsuChasuService;

    @Test
    @Transactional
    @Commit
    public void insert(){

        GumsuChasuDTO dto = GumsuChasuDTO.builder()
                .gumsuNum(12L)
                .gumsu1(LocalDateTime.of(2024,8,15,6,30))
                .memberDTO(memberService.findMemberDto(1L,null))
                .gumsuDTO(gumsuService.getGumsuById(1L))
                .build();
            gumsuChasuService.createGumsuChasu(dto);

    }
}
