package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.EmergencyDTO;
import mit.iwrcore.IWRCore.security.service.BaljuService;
import mit.iwrcore.IWRCore.security.service.EmergencyService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;


@SpringBootTest
public class EmergencyTests {
    @Autowired
    private EmergencyService emergencyService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BaljuService baljuService;


    @Test
    @Transactional
    @Commit
    public void insert(){
        BaljuDTO optionalBaljuDTO = baljuService.getBaljuById(1L);
        EmergencyDTO dto = EmergencyDTO.builder()
                .emerNum(100L)
                .emerDate(LocalDateTime.of(2024,8,25,10,50))
                .who("이우식")
                .emcheck(1L)
                .memberDTO(memberService.findMemberDto(1L,null))
                .baljuDTO(optionalBaljuDTO)
                .build();

        emergencyService.createEmergency(dto);
    }
}
