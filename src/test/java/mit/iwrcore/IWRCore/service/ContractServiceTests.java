package mit.iwrcore.IWRCore.service;


import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.repository.ContractRepository;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;


@SpringBootTest
public class ContractServiceTests {
    @Autowired
    private ContractService contractService;
    @Autowired
    private ContractRepository contractRepository;

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
    @Test
    @Transactional
    @Commit
    public void test1(){
        ContractDTO contractDTO=ContractDTO.builder()
                .conNum(2000L)
                .money(3500000L)
                .howDate(35L)
                .conDate(LocalDateTime.now().minusDays(1L))
                .filename("")
                .who("계약담당자")
                .jodalPlanDTO(jodalPlanService.findById(5L))
                .memberDTO(memberService.findMemberDto(1L, null))
                .partnerDTO(partnerService.findPartnerDto(2L, null, null))
                .build();
        contractService.createContract(contractDTO);
    }
    @Test
    @Transactional
    @Commit
    public void test12(){
        ContractDTO contractDTO=contractService.getContractById(1L);
        contractDTO.setConDate(LocalDateTime.now());
        contractService.updateContract(contractDTO);
    }
    @Test
    @Transactional
    @Commit
    public void test123(){
        Pageable pageable=PageRequest.of(0,5);

    }
    @Test
    @Transactional
    @Commit
    public void tes1234(){
        Pageable pageable= PageRequest.of(0,2);
        contractRepository.couldContractMaterial(pageable).forEach(System.out::println);
    }
    @Test
    @Transactional
    @Commit
    public void test12345(){
        System.out.println(contractRepository.detailStock(4L));
    }
    @Test
    public void test5566(){
        PageRequestDTO requestDTO=PageRequestDTO.builder().page(1).size(2).build();
        Pageable pageable= PageRequest.of(0,2);
//        System.out.println(contractRepository.noContractPage(pageable));
    }

}
