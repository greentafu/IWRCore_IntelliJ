package mit.iwrcore.IWRCore.service;


import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.*;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class JodalPlanServiceTests {
    @Autowired
    private JodalPlanServiceImpl jodalPlanService;

    @Autowired
    private JodalPlanRepository jodalPlanRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private JodalChasuRepository jodalChasuRepository;

    @Autowired
    private ProplanService proplanService;

    @Autowired
    private MemberService memberService; // MemberRepository 추가

    @Autowired
    private MaterialService materialService;


    @Test
    @Transactional
    @Commit
    void insertJplan(){
        JodalPlanDTO dto=JodalPlanDTO.builder()
                .planDate(LocalDateTime.now())
                .memberDTO(memberService.findMemberDto(1L, null))
                .proplanDTO(proplanService.findById(1L))
                .materialDTO(materialService.findM(1L))
                .build();
        jodalPlanService.save(dto);
        JodalPlanDTO dto1=JodalPlanDTO.builder()
                .planDate(LocalDateTime.now())
                .memberDTO(memberService.findMemberDto(2L, null))
                .proplanDTO(proplanService.findById(2L))
                .materialDTO(materialService.findM(2L))
                .build();
        jodalPlanService.save(dto1);
    }
    @Test
    @Transactional
    @Commit
    public void test34(){
        MemberDTO memberDTO=memberService.findMemberDto(1L, null);
        ProplanDTO proplanDTO=proplanService.findById(1L);
        jodalPlanService.saveFromProplan(proplanDTO, memberDTO);
    }
    @Test
    @Transactional
    @Commit
    public void test1231(){
        Pageable pageable= PageRequest.of(0,2);
        jodalPlanRepository.nonPlanMaterial(pageable).forEach(System.out::println);
    }
    @Test
    @Transactional
    @Commit
    public void test2222(){
        System.out.println(jodalPlanService.newJodalChasu(4L));
    }
    @Test
    @Transactional
    @Commit
    public void test1111111111(){
        System.out.println(jodalPlanRepository.stock(1L));
    }

}