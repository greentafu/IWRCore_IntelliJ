package mit.iwrcore.IWRCore.service;


import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.*;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.service.JodalPlanServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    private ProplanRepository proPlanRepository;

    @Autowired
    private MemberRepository memberRepository; // MemberRepository 추가


    @Test
    void insertJplan(){

    }

}