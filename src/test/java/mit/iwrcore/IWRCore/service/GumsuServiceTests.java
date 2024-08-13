package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuDTO;
import mit.iwrcore.IWRCore.security.service.BaljuService;
import mit.iwrcore.IWRCore.security.service.GumsuService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;

@SpringBootTest
public class GumsuServiceTests {
    @Autowired
    private BaljuService baljuService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private GumsuService gumsuService;

    @Test
    @Transactional
    @Commit
    public void insert(){
        BaljuDTO baljuDTO= baljuService.getBaljuById(1L);
        GumsuDTO dto = GumsuDTO.builder()
                .make(20L)
                .who("이우식")
                .gumsuDate(LocalDateTime.now())
                .memberDTO(memberService.findMemberDto(1L,null))
                .baljuDTO(baljuDTO)
                .build();
        gumsuService.createGumsu(dto);
    }
}
