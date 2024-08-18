package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.repository.ReturnsRepository;
import mit.iwrcore.IWRCore.security.dto.ReturnsDTO;
import mit.iwrcore.IWRCore.security.service.MemberService;
import mit.iwrcore.IWRCore.security.service.ReturnsService;
import mit.iwrcore.IWRCore.security.service.ShipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
public class ReturnsServiceTests {
    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ReturnsService returnsService;
    @Autowired
    private ReturnsRepository returnsRepository;

    @Test
    @Transactional
    @Commit
    public void insert(){
        ReturnsDTO dto =ReturnsDTO.builder()
                .reDetail("반품 내용은 비밀")
                .whyRe("이유도 비밀")
                .bGo("이건 비비고")
                .filename("파일")
                .email("asdq@asd.com")
                .shipmentDTO(shipmentService.getShipmentById(1L))
                .memberDTO(memberService.findMemberDto(1L,null))
                .build();
        returnsService.createReturns(dto);
    }
}
