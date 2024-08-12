package mit.iwrcore.IWRCore.service;


import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.*;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.service.JodalPlanServiceImpl;
import mit.iwrcore.IWRCore.security.service.MemberService;
import mit.iwrcore.IWRCore.security.service.ProductService;
import mit.iwrcore.IWRCore.security.service.ProplanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

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


    @Test
    @Transactional
    @Commit
    void insertJplan(){
        JodalPlanDTO dto=JodalPlanDTO.builder()
                .planDate(LocalDateTime.now())
                .memberDTO(memberService.findMemberDto(1L, null))
                .proplanDTO(proplanService.findById(1L))
                .build();
//        jodalPlanService.save(dto);
//        JodalPlanDTO dto1=JodalPlanDTO.builder()
//                .planDate(LocalDateTime.now())
//                .memberDTO(memberService.findMemberDto(2L, null))
//                .proplanDTO(proplanService.findById(2L))
//                .build();
//        jodalPlanService.save(dto1);
//    }

}}