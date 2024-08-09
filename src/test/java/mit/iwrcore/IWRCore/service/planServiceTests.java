package mit.iwrcore.IWRCore.service;


import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Plan;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.repository.PlanRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.security.dto.PlanDTO;
import mit.iwrcore.IWRCore.security.service.PlanService;
import mit.iwrcore.IWRCore.security.service.PlanServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class planServiceTests {
    @Autowired
    private PlanService planService;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSave() {
        // Given
        Product product = new Product();
        product.setManuCode(1L);
        productRepository.save(product);

        PlanDTO dto = PlanDTO.builder()
                .plancode(1L)
                .quantity(50L)
                .line("A")
                .productIds(List.of(1L))
                .build();

        // When
        planService.save(dto);

        // Log the saved data
        List<Plan> savedPlans = planRepository.findAll();
        savedPlans.forEach(plan -> {
            System.out.println("Saved Plan: " + plan);
        });
    }
    @Test
    public void testUpdate() {
        // Given
        Product product = new Product();
        product.setManuCode(1L);
        product.setName("Sample Product");
        productRepository.save(product);

        Plan plan = Plan.builder()
                .plancode(4L)
                .quantity(50L)
                .line("A")
                .product(product)
                .build();
        planRepository.save(plan);

        PlanDTO updatedDto = PlanDTO.builder()
                .plancode(4L)
                .quantity(100L)
                .line("B")
                .productIds(List.of(1L))
                .build();

        // When
        planService.save(updatedDto);  // Use save method to update

        // Then
        Optional<Plan> result = planRepository.findById(4L);
        result.ifPresent(planResult -> {
            System.out.println("Updated Plan: " + planResult);
        });
    }
    @Test
    public void testDeleteById() {
        // Given
        Product product = new Product();
        product.setManuCode(1L);
        product.setName("Sample Product");
        productRepository.save(product);

        Plan plan = Plan.builder()
                .plancode(1L)
                .quantity(50L)
                .line("A")
                .product(product)
                .build();
        planRepository.save(plan);

        // When
        planService.deleteById(1L);

        // Then
        Optional<Plan> result = planRepository.findById(1L);
        result.ifPresentOrElse(
                planResult -> System.out.println("Deleted Plan: " + planResult),
                () -> System.out.println("Plan not found after deletion")
        );
    }
    @Test
    public void testFindByProductNo() {
        // Given
        Product product = new Product();
        product.setManuCode(100L);
        product.setName("Sample Product");
        productRepository.save(product);

        Plan plan = Plan.builder()
                .plancode(4L)
                .quantity(100L)
                .line("B")
                .product(product)
                .build();
        planRepository.save(plan);

        // When
        List<PlanDTO> result = planService.findByProductId(100L);

        // Then
        result.forEach(planDto -> {
            System.out.println("Found PlanDTO: " + planDto);
        });
    }
}