package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.ProPlan;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.repository.ProplanRepository;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import mit.iwrcore.IWRCore.security.service.ProplanServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProplanServiceTests {

    @Autowired
    private ProplanServiceImpl proplanService;

    @Autowired
    private ProplanRepository proPlanRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testSave() {
        // Given
        Product product = new Product();
        product.setManuCode(100L);
        productRepository.save(product);

        ProplanDTO dto = ProplanDTO.builder()
                .proplanNo(1L)
                .pronum(10L)
                .filename("file.txt")
                .startDate(LocalDateTime.now().plusDays(2))
                .endDate(LocalDateTime.now().plusDays(4))
                .line("A")
                .details("Details")
                .productId(100L)
                .build();

        // When
        proplanService.save(dto);
    }
    @Test
    public void testUpdate() {
        // Given
        Product product = new Product();
        product.setManuCode(1L);
        productRepository.save(product);

        ProPlan proPlan = new ProPlan();
        proPlan.setProplanNo(1L);
        proPlan.setProduct(product);
        proPlanRepository.save(proPlan);

        ProplanDTO dto = ProplanDTO.builder()
                .proplanNo(1L)
                .pronum(20L)
                .filename("updated_file.txt")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(2))
                .line("B")
                .details("Updated Details")
                .productId(100L)
                .build();

        // When
        proplanService.update(dto);

        // Data updated in the database
        // Verify manually in the database
    }
    @Test
    public void testDeleteById() {
        // Given
        Product product = new Product();
        product.setManuCode(1L);
        productRepository.save(product);

        ProPlan proPlan = new ProPlan();
        proPlan.setProplanNo(1L);
        proPlan.setProduct(product);
        proPlanRepository.save(proPlan);

        // When
        proplanService.deleteById(1L);

        // Data deleted from the database
        // Verify manually in the database
    }
    @Test
    public void testFindById() {
        // Given
        Product product = new Product();
        product.setManuCode(1L);
        productRepository.save(product);

        ProPlan proPlan = new ProPlan();
        proPlan.setProplanNo(1L);
        proPlan.setProduct(product);
        proPlanRepository.save(proPlan);

        // When
        ProplanDTO result = proplanService.findById(1L);

        // Data retrieved from the database
        // Verify manually in the database
        System.out.println("Result from findById: " + result);

    }
    @Test
    public void testFindByPlanId() {
        // Given
        Product product = new Product();
        product.setManuCode(1L);
        productRepository.save(product);

        ProPlan proPlan = new ProPlan();
        proPlan.setProplanNo(1L);
        proPlan.setProduct(product);
        proPlanRepository.save(proPlan);

        // When
        List<ProplanDTO> results = proplanService.findByPlanId(1L);
        System.out.println("Results from findByPlanId: " + results);

        // Data retrieved from the database
        // Verify manually in the database
    }
}
