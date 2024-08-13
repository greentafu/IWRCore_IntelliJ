package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.security.dto.InvoiceDTO;
import mit.iwrcore.IWRCore.security.service.InvoiceService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;

@SpringBootTest
public class InvoiceServiceTests {
        @Autowired
        private MemberService memberService;
        @Autowired
        private InvoiceService invoiceService;

    @Test
    @Transactional
    @Commit
    public void insert(){
        InvoiceDTO dto = InvoiceDTO.builder()
                .plz(2L)
                .dateCreated(LocalDateTime.now())
                .filename("asd")
                .memberDTO(memberService.findMemberDto(1L,null))
                .build();

        invoiceService.createInvoice(dto);
    }


}
