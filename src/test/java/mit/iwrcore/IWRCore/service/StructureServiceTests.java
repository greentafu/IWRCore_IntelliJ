package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.entity.Structure;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.repository.StructureRepository;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
public class StructureServiceTests {
    @Autowired
    private StructureServiceImpl structureService;
    @Autowired
    private  MaterialService materialService;
    @Autowired
    private ProductService productService;

    @Autowired
    private StructureRepository structureRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductServiceImpl productServicei;
    @Autowired
    private MaterialServiceImpl materialServiceImpl;

    private static Material material;
    private static Product product;
    private static Structure structure;



    @Test
    @Transactional
    @Commit
    public void test(){
        Structure structure1=Structure.builder()
                .material(materialServiceImpl.materdtoToEntity(materialService.findM(1L)))
                .product(productServicei.productDtoToEntity(productService.getProductById(3L)))
                .quantity(70).build();
        structureRepository.save(structure1);
        Structure structure2=Structure.builder()
                .material(materialServiceImpl.materdtoToEntity(materialService.findM(2L)))
                .product(productServicei.productDtoToEntity(productService.getProductById(3L)))
                .quantity(60).build();
        structureRepository.save(structure2);
        Structure structure4=Structure.builder()
                .material(materialServiceImpl.materdtoToEntity(materialService.findM(3L)))
                .product(productServicei.productDtoToEntity(productService.getProductById(3L)))
                .quantity(80).build();
        structureRepository.save(structure4);
    }
    @Test
    @Transactional
    @Commit
    public void test1(){
        StructureDTO structureDTO=StructureDTO.builder()
                .productDTO(productService.getProductById(3L))
                .materialDTO(materialService.findM(3L))
                .quantity(10L)
                .build();
        structureService.save(structureDTO);
//        structureService.update(structureDTO);
    }

    @Transactional
    @Commit
    @Test
    void testSave() {
        // Given
        Material material = materialRepository.findById(1L).orElse(null);
        Product product = productRepository.findById(1L).orElse(null);

        StructureDTO dto = StructureDTO.builder()
                .sno(2L) // Assumed ID for testing, should be unique or managed
                .materialDTO(MaterialDTO.builder().materCode(material.getMaterCode()).build())
                .productDTO(ProductDTO.builder().manuCode(product.getManuCode()).build())
                .quantity(10L)
                .build();

        // When
        structureService.save(dto);

        // Log or manual verification
        System.out.println("Saved Structure: " + structureRepository.findById(dto.getSno()));
    }
//
//    @Test
//    @Transactional
//    @Commit
//    void testUpdate() {
//        // Given
//        Structure existingStructure = structureRepository.findById(1L).orElse(null);
//
//        if (existingStructure != null) {
//            existingStructure.setQuantity(20L);
//
//            // When
//            structureService.update(existingStructure);
//
//            // Log or manual verification
//            System.out.println("Updated Structure: " + structureRepository.findById(existingStructure.getSno()));
//        }
//    }
    @Transactional
    @Commit
    @Test
    void testDeleteById() {
        // Given
        Long id = 4L;
        Structure structure = structureRepository.findById(id).orElse(null);

        if (structure != null) {
            // When
            structureService.deleteById(id);

            // Log or manual verification
            System.out.println("Remaining Structures Count: " + structureRepository.count());
        }
    }

//    @Test
//    void testFindByProduct_ManuCode() {
//        // Given
//        Long manuCode = 1L;
//        Structure structure = Structure.builder()
//                .sno(2L)
//                .material(materialRepository.findById(1L).orElse(null))
//                .product(productRepository.findById(manuCode).orElse(null))
//                .quantity(10L)
//                .build();
//        structureRepository.save(structure);
//
//        // When
//        List<StructureDTO> result = structureService.findByProduct_ManuCode(manuCode);
//
//        // Log or manual verification
//        System.out.println("Found Structures: " + result);
//    }
    @Test
    @Transactional
    @Commit
    public void test132131(){
        System.out.println(structureService.findByProduct_ManuCode(3L));
    }
}


