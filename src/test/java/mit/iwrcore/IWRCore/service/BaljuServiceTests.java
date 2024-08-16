package mit.iwrcore.IWRCore.service;


import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.repository.BaljuRepository;
import mit.iwrcore.IWRCore.repository.ContractRepository;
import mit.iwrcore.IWRCore.repository.MemberRepository;

import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.service.BaljuService;

import mit.iwrcore.IWRCore.security.service.ContractService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class BaljuServiceTests {
    @Autowired
    private BaljuService baljuService;
    @Autowired
    private BaljuRepository baljuRepository;

    @Autowired
    private MemberService memberService;
    @Autowired
    private ContractService contractService;


    @Test
    @Transactional
    @Commit
    public void insert(){
//        Optional<ContractDTO> optionalContractDTO = contractService.getContractById(1L);
//
//        // BaljuDTO를 생성
//        BaljuDTO dto = BaljuDTO.builder()
//                .baljuNum(100L)
//                .baljuDate(LocalDateTime.of(2024, 8, 20, 10, 20))
//                .baljuWhere("집앞")
//                .baljuPlz("조용히")
//                .filename("avc")
//                .memberDTO(memberService.findMemberDto(1L, null))
//                .contractDTO(optionalContractDTO.orElse(null)) // Optional에서 값을 추출
//                .build();
//
//
//        baljuService.createBalju(dto);
    }
    @Test
    @Transactional
    @Commit
    public void test1(){
        Pageable pageable= PageRequest.of(0, 2);
        System.out.println(baljuRepository.couldBalju(pageable));
    }
    @Test
    @Transactional
    @Commit
    public void test12(){
        BaljuDTO baljuDTO=BaljuDTO.builder()
                .baljuNum(2000L)
                .baljuDate(LocalDateTime.now().plusDays(6L))
                .baljuWhere("경기도 수원시")
                .baljuPlz("잘")
                .filename("")
                .memberDTO(memberService.findMemberDto(1L, null))
                .contractDTO(contractService.getContractById(1L))
                .build();
        baljuService.createBalju(baljuDTO);
    }
    @Test
    @Transactional
    @Commit
    public void test1234(){
        PageRequestDTO2 requestDTO=PageRequestDTO2.builder().page2(1).size2(2).build();
        System.out.println(baljuService.finishedBalju(requestDTO));
    }
    @Test
    @Transactional
    @Commit
    public void test11(){
        PageRequestDTO requestDTO=PageRequestDTO.builder().page(1).size(2).pno(2L).build();
        System.out.println(baljuService.partnerBaljuList(requestDTO));
    }

}
