package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.security.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTests {

    @Autowired
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Autowired
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @Commit
    public void testAddProduct() {
        // Given
        ProductDTO productDTO = ProductDTO.builder()
                //.manuCode(1L)  // manuCode가 자동 생성되므로 설정할 필요 없음
                .name("Sample Product")
                .color("Red")

                .text("Sample Text")
                       .uuid("sample-uuid")
                .supervisor("John Doe")
                .mater_imsi("12345")
                .mater_check("Checked")
                .build();

        Product product = productService.productDtoToEntity(productDTO);
        productRepository.save(product);
    }


    @Test
    public void testGetProductById() {
        // Given
        Product product = Product.builder()
                .manuCode(1L)
                .name("Sample Product")
                .color("Red")
                .text("Sample Text")
                .uuid("sample-uuid")
                .supervisor("John Doe")
                .mater_imsi("12345")
                .mater_check("Checked")
                .build();
        productRepository.save(product);

        // When
        ProductDTO result = productService.getProductById(1L);

        // Then
        assertNotNull(result);
        assertEquals(product.getManuCode(), result.getManuCode());
        assertEquals(product.getName(), result.getName());
    }

    @Test
    public void testUpdateProduct() {
        // Given
        Product product = Product.builder()
                .manuCode(1L)
                .name("Sample Product")
                .color("Red")
                .text("Sample Text")
                .uuid("sample-uuid")
                .supervisor("John Doe")
                .mater_imsi("12345")
                .mater_check("Checked")
                .build();
        productRepository.save(product);

        ProductDTO productDTO = ProductDTO.builder()
                .manuCode(1L)
                .name("Updated Product")
                .color("Blue")
                .text("Updated Text")
                .uuid("updated-uuid")
                .supervisor("Jane Doe")
                .mater_imsi("67890")
                .mater_check("Unchecked")
                .build();

        // When
        ProductDTO result = productService.updateProduct(productDTO);

        // Then
        assertNotNull(result, "Result should not be null");
        assertEquals(productDTO.getName(), result.getName());
        assertEquals(productDTO.getColor(), result.getColor());
        assertEquals(productDTO.getText(), result.getText());
        assertEquals(productDTO.getUuid(), result.getUuid());
        assertEquals(productDTO.getSupervisor(), result.getSupervisor());
        assertEquals(productDTO.getMater_imsi(), result.getMater_imsi());
        assertEquals(productDTO.getMater_check(), result.getMater_check());
    }



    @Test
    public void testDeleteProduct() {
        // Given
        Product product = Product.builder()
                .manuCode(1L)
                .name("Sample Product")
                .color("Red")
                .text("Sample Text")
                .uuid("sample-uuid")
                .supervisor("John Doe")
                .mater_imsi("12345")
                .mater_check("Checked")
                .build();
        productRepository.save(product);

        // When
        productService.deleteProduct(1L);

        // Then
        assertFalse(productRepository.findById(1L).isPresent(), "Product should be deleted");
    }


}
