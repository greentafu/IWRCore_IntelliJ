package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.security.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
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

    @Test
    @Transactional
    @Commit
    public void allTest(){
        List<ProductDTO> list =productService.getAllProducts();
        list.forEach(productDTO -> {
            System.out.println("ProductDTO details:");
            System.out.println("ManuCode: " + productDTO.getManuCode());
            System.out.println("Name: " + productDTO.getName());
            System.out.println("Color: " + productDTO.getColor());
            System.out.println("Text: " + productDTO.getText());
            System.out.println("UUID: " + productDTO.getUuid());
            System.out.println("Supervisor: " + productDTO.getSupervisor());
            System.out.println("Mater_IMSI: " + productDTO.getMater_imsi());
            System.out.println("Mater_Check: " + productDTO.getMater_check());
            System.out.println("-------------");
        });

    }
}
