package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.repository.ContractRepository;
import mit.iwrcore.IWRCore.repository.PartnerRepository;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PartnerDTO;
import mit.iwrcore.IWRCore.security.service.ContractServiceImpl;
import mit.iwrcore.IWRCore.security.service.JodalPlanService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import mit.iwrcore.IWRCore.security.service.PartnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

@SpringBootTest
public class ContractServiceTests {
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private JodalPlanService jodalPlanService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private ContractServiceImpl contractServiceImpl;


    @Test
    public void createContractTest() {

        MemberDTO memberDTO = memberService.findMemberDto(1L, null);
        JodalPlanDTO jodalPlanDTO = jodalPlanService.findById(1L);
        PartnerDTO partnerDTO = partnerService.findPartnerDto(1L,"param1", "param2");

        ContractDTO contractDTO = ContractDTO.builder()
                .conNo(null) // ID는 null로 설정, 생성 시 자동으로 설정됨
                .conNum(100L)
                .money(5000L)
                .howDate(30L)
                .conDate(LocalDateTime.now())
                .filename("contract.pdf")
                .who("John Doe")
                .jodalPlanDTO(jodalPlanDTO)
                .memberDTO(memberDTO)
                .partnerDTO(partnerDTO)
                .build();

        // When
        ContractDTO createdContractDTO = contractServiceImpl.createContract(contractDTO);

        // Then
        System.out.println("Created ContractDTO: " + createdContractDTO);
        // Check database manually for the created contract
    }
}
