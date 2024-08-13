package mit.iwrcore.IWRCore.service;


import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;


@SpringBootTest
public class ContractServiceTests {
    @Autowired
    private ContractService contractService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private JodalPlanService jodalPlanService;

    @Autowired
    private PartnerService partnerService;

    @Test
    @Transactional
    @Commit
    public void testCreateContract() {

        // Given
        ContractDTO dto = ContractDTO.builder()

                .conNum(1L)
                .money(1000L)
                .howDate(2L)
                .conDate(LocalDateTime.now().plusDays(3))
                .filename("contract.pdf")
                .who("John Doe")
                .memberDTO(memberService.findMemberDto(1L,null))
                .jodalPlanDTO(jodalPlanService.findById(1L))
                .partnerDTO(partnerService.findPartnerDto(1L,null,null))
                .build();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+dto);
       contractService.createContract(dto);
    }




}
