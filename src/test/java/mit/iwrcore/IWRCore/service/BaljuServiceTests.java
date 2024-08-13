package mit.iwrcore.IWRCore.service;


import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.repository.BaljuRepository;
import mit.iwrcore.IWRCore.repository.ContractRepository;
import mit.iwrcore.IWRCore.repository.MemberRepository;

import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.service.BaljuService;

import mit.iwrcore.IWRCore.security.service.ContractService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class BaljuServiceTests {
    @Autowired
    private BaljuService baljuService;

    @Autowired
    private MemberService memberService;
    @Autowired
    private ContractService contractService;


    @Test
    @Transactional
    @Commit
    public void insert(){
        Optional<ContractDTO> optionalContractDTO = contractService.getContractById(1L);

        // BaljuDTO를 생성
        BaljuDTO dto = BaljuDTO.builder()
                .baljuNum(100L)
                .baljuDate(LocalDateTime.of(2024, 8, 20, 10, 20))
                .baljuWhere("집앞")
                .baljuPlz("조용히")
                .filename("avc")
                .memberDTO(memberService.findMemberDto(1L, null))
                .contractDTO(optionalContractDTO.orElse(null)) // Optional에서 값을 추출
                .build();


        baljuService.createBalju(dto);
    }
}
