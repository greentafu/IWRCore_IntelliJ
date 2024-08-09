package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;

import mit.iwrcore.IWRCore.entity.Contract;
import mit.iwrcore.IWRCore.entity.JodalChasu;
import mit.iwrcore.IWRCore.entity.JodalPlan;
import mit.iwrcore.IWRCore.entity.ProPlan;
import mit.iwrcore.IWRCore.repository.ContractRepository;
import mit.iwrcore.IWRCore.repository.JodalChasuRepository;
import mit.iwrcore.IWRCore.repository.JodalPlanRepository;
import mit.iwrcore.IWRCore.repository.ProplanRepository;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.service.JodalPlanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;


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

    @Test
    void testSave() {
        // 데이터베이스에 필요한 테스트 데이터를 추가합니다.
        Contract contract = contractRepository.save(new Contract());
        JodalChasu jodalChasu = jodalChasuRepository.save(new JodalChasu());
        ProPlan proPlan = proPlanRepository.save(new ProPlan());

        JodalPlanDTO jodalPlanDTO = JodalPlanDTO.builder()
                .joNo(null) // 신규 저장 시 ID는 null이어야 함
                .details("Test details")
                .planDate(LocalDateTime.now())
                .writerId(1L) // 테스트용 작성자 ID
                .contractId(contract.getConNo())
                .jodalChasuIds(Collections.singletonList(jodalChasu.getJoNum()))
                .proPlanId(proPlan.getProplanNo())
                .build();

        jodalPlanService.save(jodalPlanDTO);

        // 저장된 계획을 데이터베이스에서 조회
        JodalPlan savedPlan = jodalPlanRepository.findById(jodalPlanDTO.getJoNo()).orElse(null);
        System.out.println("Saved JodalPlan: " + savedPlan);
    }

    }
