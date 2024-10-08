package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.GumsuChasu;
import mit.iwrcore.IWRCore.repository.GumsuChasuRepository;
import mit.iwrcore.IWRCore.security.dto.GumsuChasuDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.service.GumsuChasuService;
import mit.iwrcore.IWRCore.security.service.GumsuService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class GumsuChasuTests {
    @Autowired
    private MemberService memberService;
    @Autowired
    private GumsuService gumsuService;
    @Autowired
    private GumsuChasuService gumsuChasuService;
    @Autowired
    private GumsuChasuRepository gumsuChasuRepository;

    @Test
    @Transactional
    @Commit
    public void insert(){
//        GumsuChasuDTO dto = GumsuChasuDTO.builder()
//                .gumsuNum(450L)
//                .gumsuDate(LocalDateTime.of(2024,8,15,6,30))
//                .memberDTO(memberService.findMemberDto(1L,null))
//                .gumsuDTO(gumsuService.getGumsuById(1L))
//                .build();
//        gumsuChasuService.createGumsuChasu(dto);
//        GumsuChasuDTO dto2 = GumsuChasuDTO.builder()
//                .gumsuNum(325L)
//                .gumsuDate(LocalDateTime.of(2024,8,22,6,30))
//                .memberDTO(memberService.findMemberDto(1L,null))
//                .gumsuDTO(gumsuService.getGumsuById(1L))
//                .build();
//        gumsuChasuService.createGumsuChasu(dto2);
//        GumsuChasuDTO dto3 = GumsuChasuDTO.builder()
//                .gumsuNum(325L)
//                .gumsuDate(LocalDateTime.of(2024,8,29,6,30))
//                .memberDTO(memberService.findMemberDto(1L,null))
//                .gumsuDTO(gumsuService.getGumsuById(1L))
//                .build();
//        gumsuChasuService.createGumsuChasu(dto3);
    }
    @Test
    @Transactional
    @Commit
    public void test12(){
        PageRequestDTO requestDTO=PageRequestDTO.builder().page(1).size(2).build();
        System.out.println(gumsuChasuService.getAllGumsuChasuContract(requestDTO));
    }
    @Test
    @Transactional
    @Commit
    public void test123123(){
        Pageable pageable= PageRequest.of(0,2);
        System.out.println(gumsuChasuRepository.getAllGumsuChasuContract(pageable));
    }
}
