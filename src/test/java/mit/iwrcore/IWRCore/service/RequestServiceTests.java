package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.RequestDTO;
import mit.iwrcore.IWRCore.security.service.MaterialService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import mit.iwrcore.IWRCore.security.service.ProplanService;
import mit.iwrcore.IWRCore.security.service.RequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class RequestServiceTests {
    @Autowired
    private RequestService requestService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private ProplanService proplanService;

    @Test
    public void insertRequest() {
        // Prepare RequestDTO
//        RequestDTO requestDTO = RequestDTO.builder()
//                .eventDate(LocalDateTime.now())
//                .text("Sample Request")
//                .reqCheck(1L)
//                .line("A")
//                .memberDTO(memberService.findMemberDto(1L, null))
//                .build();
//
//        // Save Request
//        requestService.createRequest(requestDTO);
    }
    @Test
    @Transactional
    @Commit
    public void test1(){
//        RequestDTO requestDTO=RequestDTO.builder()
//                .requestNum(500L)
//                .eventDate(LocalDateTime.now().plusDays(30L))
//                .text("잘")
//                .reqCheck(0L)
//                .line("A")
//                .proplanDTO(proplanService.findById(1L))
//                .materialDTO(materialService.findM(1L))
//                .memberDTO(memberService.findMemberDto(1L, null))
//                .build();
//        requestService.createRequest(requestDTO);
//        RequestDTO requestDTO2=RequestDTO.builder()
//                .requestNum(300L)
//                .eventDate(LocalDateTime.now().plusDays(30L))
//                .text("잘")
//                .reqCheck(0L)
//                .line("A")
//                .proplanDTO(proplanService.findById(1L))
//                .materialDTO(materialService.findM(2L))
//                .memberDTO(memberService.findMemberDto(1L, null))
//                .build();
//        requestService.createRequest(requestDTO2);
//        RequestDTO requestDTO3=RequestDTO.builder()
//                .requestNum(300L)
//                .eventDate(LocalDateTime.now().plusDays(30L))
//                .text("잘")
//                .reqCheck(0L)
//                .line("B")
//                .proplanDTO(proplanService.findById(1L))
//                .materialDTO(materialService.findM(3L))
//                .memberDTO(memberService.findMemberDto(1L, null))
//                .build();
//        requestService.createRequest(requestDTO3);
    }
    @Test
    @Transactional
    @Commit
    public void test12(){
        PageRequestDTO requestDTO=PageRequestDTO.builder().page(1).size(2).build();
        System.out.println(requestService.requestPage(requestDTO));
    }

}