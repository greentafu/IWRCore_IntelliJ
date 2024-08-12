package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.security.dto.RequestDTO;
import mit.iwrcore.IWRCore.security.service.MaterialService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import mit.iwrcore.IWRCore.security.service.ProplanService;
import mit.iwrcore.IWRCore.security.service.RequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class RequestServiceTests {
    @Autowired
    private RequestService requestService;

    @Autowired
    private MemberService memberService;

    @Test
    public void insertRequest() {
        // Prepare RequestDTO
        RequestDTO requestDTO = RequestDTO.builder()
                .eventDate(LocalDate.now())
                .text("Sample Request")
                .reqCheck(1L)
                .line("A")
                .memberDTO(memberService.findMemberDto(1L, null))
                .build();

        // Save Request
        requestService.createRequest(requestDTO);
    }

}