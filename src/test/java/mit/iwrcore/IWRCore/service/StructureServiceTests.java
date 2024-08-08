package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.entity.Structure;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.repository.StructureRepository;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;
import mit.iwrcore.IWRCore.security.service.MaterService;
import mit.iwrcore.IWRCore.security.service.MaterialService;
import mit.iwrcore.IWRCore.security.service.ProductService;
import mit.iwrcore.IWRCore.security.service.StructureServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
public class StructureServiceTests {
//    @Autowired
//    private StructureServiceImpl structureService;
//    @Autowired
//    private  MaterialService materialService;
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private StructureRepository structureRepository;
//
//    @Autowired
//    private MaterialRepository materialRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    private static Material material;
//    private static Product product;
//    private static Structure structure;
//
//
//
//    @Test
//    @Transactional
//    @Commit
//    public void test(){
//        Structure structure1=Structure.builder()
//                .material(materialService.materEntity(materialService.findM(1L)))
//                .product(productService.productDtoToEntity(productService.getProductById(1L)))
//                .quantity(100).build();
//        structureRepository.save(structure1);
//    }
//    @Test
//    @Transactional
//    @Commit
//    public void setUp() {
//        // Set up material
//        material = new Material();
//        material.setName("Steel");
//        material.setUnit("kg");
//        material.setStandard("ISO");
//        material.setColor("Gray");
//        material.setFile("steel.jpg");
//        materialRepository.save(material);
//
//        // Set up product
//        product = new Product();
//        product.setName("Widget");
//        product.setColor("Blue");
//        product.setText("A blue widget");
//        product.setUuid("1234-5678-9012");
//        product.setSupervisor("John Doe");
//        productRepository.save(product);
//
//         Set up structure
//        Material material1=materialService.materEntity(materialService.findM(1L));
//        System.out.println("@@@@@@@@@@@@@@@"+material1);
//        Product product1=productService.productDtoToEntity(productService.getProductById(1L));
//        System.out.println("###################"+product1);
//        Material material1=Material.builder().materCode(1L).build();
//        Product product1=Product.builder().manuCode(1L).build();
//
//        Structure structure = Structure.builder()
//                .material(material1)
//                .product(product1)
//                .quantity(100).build();
//        structureRepository.save(structure);
//    }


}