package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.repository.ContractRepository;
import mit.iwrcore.IWRCore.repository.PartnerRepository;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.service.ContractServiceImpl;
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
    private PartnerRepository partnerRepository;

    @Autowired
    private ContractServiceImpl contractService;

    @Autowired
    private ContractDTO contractDTO;

    @Test
    @Sql(statements = "INSERT INTO partner (pno, name) VALUES (1, 'Partner One')")
    public void testSave() {
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setConNo(1L);
        contractDTO.setConNum(10L);
        contractDTO.setMoney(1000L);
        contractDTO.setHowDate(15L);
        contractDTO.setConDate(LocalDateTime.now());
        contractDTO.setFilename("contract.pdf");
        contractDTO.setWho("John Doe");
        contractDTO.setPartnerId(1L);

        contractService.save(contractDTO);


    }
}
