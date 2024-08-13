package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.security.service.MemberService;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import mit.iwrcore.IWRCore.security.service.ProductService;
import mit.iwrcore.IWRCore.security.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private ProCodeService proCodeService;
    @Autowired
    private MemberService memberService;
    // 데이터 삽입
    @Test
    @Transactional
    @Commit
    public void insert(){
        ProductDTO dto=ProductDTO.builder()
                .name("A자전거").color("빨강").text("").uuid("")
                .supervisor("감독자1").mater_imsi(0L).mater_check(0L)
                .memberDTO(memberService.findMemberDto(1L, null))
                .proSDTO(proCodeService.findProS(1L))
                .build();
        productRepository.save(productServiceImpl.productDtoToEntity(dto));

//        ProductDTO dto1=ProductDTO.builder()
//                .name("A자전거").color("빨강").text("").uuid("")
//                .supervisor("감독자1").mater_imsi(0L).mater_check(0L)
//                .memberDTO(memberService.findMemberDto(2L, null))
//                .proSDTO(proCodeService.findProS(2L))
//                .build();
//        productRepository.save(productServiceImpl.productDtoToEntity(dto1));
//
//        ProductDTO dto2=ProductDTO.builder()
//                .name("A자전거").color("빨강").text("").uuid("")
//                .supervisor("감독자1").mater_imsi(0L).mater_check(0L)
//                .memberDTO(memberService.findMemberDto(2L, null))
//                .proSDTO(proCodeService.findProS(3L))
//                .build();
//        productRepository.save(productServiceImpl.productDtoToEntity(dto2));
//
//        ProductDTO dto3=ProductDTO.builder()
//                .name("A자전거").color("빨강").text("").uuid("")
//                .supervisor("감독자1").mater_imsi(0L).mater_check(0L)
//                .memberDTO(memberService.findMemberDto(3L, null))
//                .proSDTO(proCodeService.findProS(4L))
//                .build();
//        productRepository.save(productServiceImpl.productDtoToEntity(dto3));
    }
//
//    // 제품 한개 가져오기
//    @Test
//    @Transactional
//    @Commit
//    public void testOne(){
//        System.out.println(productService.getProductById(4L));
//    }
//
//    // 제품 여러개 가져오기
//    @Test
//    @Transactional
//    @Commit
//    public void testList(){
//        Pageable pageable= PageRequest.of(0,2);
//        productRepository.findAllProduct(pageable).forEach(System.out::println);
////        productService.getAllProducts().forEach(System.out::println);
//    }
//
//    @Test
//    @Transactional
//    @Commit
//    public void testAddProduct() {
//        ProductDTO dto3=ProductDTO.builder()
//                .manuCode(5L)
//                .name("C자전거").color("검정").text("").uuid("")
//                .supervisor("감독자2").mater_imsi(0L).mater_check(0L)
//                .memberDTO(memberService.findMemberDto(1L, null))
//                .proSDTO(proCodeService.findProS(2L))
//                .build();
////        productService.addProduct(dto3);
//        productService.updateProduct(dto3);
//    }
//    @Test
//    @Transactional
//    @Commit
//    public void deleteTest(){
//        productService.deleteProduct(5L);
//    }
    @Test
    @Transactional
    @Commit
    public void test23(){
        Pageable pageable=PageRequest.of(0,2);
//           productRepository.findNonPlanProduct(pageable).forEach(System.out::println);
        productRepository.findNonCheckProduct(pageable).forEach(System.out::println);
    }


//
//    @Test
//    public void testUpdateProduct() {
//        // Given
//        Product product = Product.builder()
//                .manuCode(1L)
//                .name("Sample Product")
//                .color("Red")
//                .text("Sample Text")
//                .uuid("sample-uuid")
//                .supervisor("John Doe")
//                .mater_imsi("12345")
//                .mater_check("Checked")
//                .build();
//        productRepository.save(product);
//
//        ProductDTO productDTO = ProductDTO.builder()
//                .manuCode(1L)
//                .name("Updated Product")
//                .color("Blue")
//                .text("Updated Text")
//                .uuid("updated-uuid")
//                .supervisor("Jane Doe")
//                .mater_imsi("67890")
//                .mater_check("Unchecked")
//                .build();
//
//        // When
//        ProductDTO result = productService.updateProduct(productDTO);
//
//        // Then
//        assertNotNull(result, "Result should not be null");
//        assertEquals(productDTO.getName(), result.getName());
//        assertEquals(productDTO.getColor(), result.getColor());
//        assertEquals(productDTO.getText(), result.getText());
//        assertEquals(productDTO.getUuid(), result.getUuid());
//        assertEquals(productDTO.getSupervisor(), result.getSupervisor());
//        assertEquals(productDTO.getMater_imsi(), result.getMater_imsi());
//        assertEquals(productDTO.getMater_check(), result.getMater_check());
//    }
//
//
//
//    @Test
//    public void testDeleteProduct() {
//        // Given
//        Product product = Product.builder()
//                .manuCode(1L)
//                .name("Sample Product")
//                .color("Red")
//                .text("Sample Text")
//                .uuid("sample-uuid")
//                .supervisor("John Doe")
//                .mater_imsi("12345")
//                .mater_check("Checked")
//                .build();
//        productRepository.save(product);
//
//        // When
//        productService.deleteProduct(1L);
//
//        // Then
//        assertFalse(productRepository.findById(1L).isPresent(), "Product should be deleted");
//    }


}