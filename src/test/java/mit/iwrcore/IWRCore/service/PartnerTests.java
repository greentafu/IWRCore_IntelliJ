package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.repository.PartnerRepository;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PartnerDTO;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import mit.iwrcore.IWRCore.security.service.PartnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class PartnerTests {
    @Autowired
    public PartnerRepository partnerRepository;

    @Autowired
    public PartnerService partnerService;
    @Autowired
    public PartCodeService partCodeService;

    @Test
    @Transactional
    @Commit
    public void findPartner(){
//        System.out.println(partnerRepository.findPartner(null, null, "123-45-67890"));
        System.out.println(partnerService.findPartnerEntity(1L, null, null));
        System.out.println(partnerService.findPartnerDto(null, "partner1_67890", null));
    }
    @Test
    @Transactional
    @Commit
    public void findList(){
//        Pageable pageable= PageRequest.of(0,5,Sort.by("pno").descending());
//        partnerRepository.findPartnerList(pageable).forEach(System.out::println);

        PageRequestDTO pageRequestDTO=PageRequestDTO.builder().page(1).size(3).build();
        System.out.println(partnerService.findPartnerList(pageRequestDTO));
    }
    @Test
    @Transactional
    @Commit
    public void insertPartner(){
        PartnerDTO partnerDTO=PartnerDTO.builder()
                .pno(1L).id("aaa2").registrationNumber("123-45-67892")
                .partSDTO(partCodeService.findPartS(1L)).build();
        System.out.println("**********"+partnerService.insertPartner(partnerDTO));
    }
    @Test
    @Transactional
    @Commit
    public void deletePartner(){
        partnerService.deletePartner(1L);
    }
}
