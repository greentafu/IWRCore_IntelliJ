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
        PageRequestDTO requestDTO=PageRequestDTO.builder().size(2).page(1).build();
//        jodalPlanRepository.nonPlanMaterial2(pageable).forEach(System.out::println);
//        System.out.println(jodalPlanRepository.noContract(pageable));
        System.out.println(jodalPlanService.noContract(requestDTO));
    }
    @Test
    @Transactional
    @Commit
    public void test2222(){
        System.out.println(jodalPlanRepository.stock(1L));
    }
    @Test
    @Transactional
    @Commit
    public void test1111111111(){
        List<Object[]> list=jodalPlanRepository.stock2(1L);
        list.forEach(System.out::println);
    }

    @Test
    public void test333(){
        System.out.println("###"+jodalPlanRepository.noneContractJodalPlan());
    }
    @Test
    @Transactional
    @Commit
    public void test13131(){
//        System.out.println(jodalPlanService.newJodalChasu(1L));
//        System.out.println(jodalPlanService.findById(1L));
        JodalPlan jodalPlan=(JodalPlan) jodalPlanRepository.getJodalPlan(1L).get(0)[0];
        System.out.println(jodalPlanService.entityToDTO(jodalPlan));
    }
}