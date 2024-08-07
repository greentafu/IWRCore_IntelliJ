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

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProduct() {
        // Given
        ProductDTO productDTO = ProductDTO.builder()
                .manuCode(1L)  // manuCode가 자동 생성되므로 설정할 필요 없음
                .name("Sample Product")
                .color("Red")
                .text("Sample Text")
                .uuid("sample-uuid")
                .supervisor("John Doe")
                .mater_imsi("12345")
                .mater_check("Checked")
                .build();

        Product product = Product.builder()  // 직접 엔티티를 생성
                .name("Sample Product")
                .color("Red")
                .text("Sample Text")
                .uuid("sample-uuid")
                .supervisor("John Doe")
                .mater_imsi("12345")
                .mater_check("Checked")
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(product);

        // When
        ProductDTO result = productService.addProduct(productDTO);

        // Then
        assertNotNull(result);
        assertEquals(productDTO.getName(), result.getName());
        assertEquals(productDTO.getColor(), result.getColor());
        assertEquals(productDTO.getText(), result.getText());
        assertEquals(productDTO.getUuid(), result.getUuid());
        assertEquals(productDTO.getSupervisor(), result.getSupervisor());
        assertEquals(productDTO.getMater_imsi(), result.getMater_imsi());
        assertEquals(productDTO.getMater_check(), result.getMater_check());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    @Transactional
    @Commit
    public void testGetProductById() {
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

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDTO result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(product.getManuCode(), result.getManuCode());
        assertEquals(product.getName(), result.getName());
        verify(productRepository, times(1)).findById(1L);
    }
    @Test
    @Transactional
    @Commit
    public void testUpdateProduct() {
        // Given
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

        Product updatedProduct = Product.builder()
                .manuCode(1L)
                .name("Updated Product")
                .color("Blue")
                .text("Updated Text")
                .uuid("updated-uuid")
                .supervisor("Jane Doe")
                .mater_imsi("67890")
                .mater_check("Unchecked")
                .build();

        // Mocking the behavior of the repository
        when(productRepository.existsById(1L)).thenReturn(true);
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

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
        verify(productRepository, times(1)).save(any(Product.class));
    }



    @Test
    @Transactional
    @Commit
    public void testUpdateProductNotFound() {
        ProductDTO productDTO = ProductDTO.builder()
                .manuCode(1L)
                .name("Updated Product")
                .build();

        when(productRepository.existsById(1L)).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(productDTO);
        });

        assertEquals("Product not found", thrown.getMessage());
        verify(productRepository, times(1)).existsById(1L);
    }

    @Test
    @Transactional
    @Commit
    public void testDeleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    @Transactional
    @Commit
    public void testDeleteProductNotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            productService.deleteProduct(1L);
        });

        assertEquals("Product not found", thrown.getMessage());
        verify(productRepository, times(1)).existsById(1L);
    }

    @Test
    @Transactional
    @Commit
    public void testGetAllProducts() {
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

        when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductDTO> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(product.getName(), result.get(0).getName());
        verify(productRepository, times(1)).findAll();
    }
}
