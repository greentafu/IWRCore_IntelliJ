package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.entity.Structure;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.repository.StructureRepository;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;
import mit.iwrcore.IWRCore.security.service.StructureServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StructureServiceTests {
    @Autowired
    private StructureServiceImpl structureService;

    @Autowired
    private StructureRepository structureRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ProductRepository productRepository;

    private static Material material;
    private static Product product;
    private static Structure structure;

    @BeforeAll
    static void setUpClass(@Autowired MaterialRepository materialRepository,
                           @Autowired ProductRepository productRepository,
                           @Autowired StructureRepository structureRepository) {
        // 테스트 데이터베이스에 객체를 한 번만 생성하고 저장
        material = materialRepository.save(Material.builder()
                .name("Material1")
                .unit("Unit1")
                .standard("Standard1")
                .color("Color1")
                .file("File1")
                .build());

        product = productRepository.save(Product.builder()
                .name("Product1")
                .color("Color1")
                .text("Text1")
                .uuid("UUID1")
                .supervisor("Supervisor1")
                .mater_imsi("IMSI1")
                .mater_check("Check1")
                .build());

        structure = structureRepository.save(Structure.builder()
                .sno(1L)
                .material(material)
                .product(product)
                .quantity(10)
                .build());
    }

    @Test
    void testSave() {
        // 테스트를 위한 새로운 구조체 생성
        Structure newStructure = Structure.builder()
                .sno(2L)
                .material(material)
                .product(product)
                .quantity(15)
                .build();

        Structure savedStructure = structureService.save(newStructure);
        System.out.println("Saved Structure: " + savedStructure);
    }

    @Test
    void testUpdate() {
        // 구조체를 업데이트
        structure.setQuantity(20);
        Structure updatedStructure = structureService.update(structure);
        System.out.println("Updated Structure: " + updatedStructure);
    }

    @Test
    void testDeleteById() {
        structureService.deleteById(structure.getSno());
        Structure deletedStructure = structureRepository.findById(structure.getSno()).orElse(null);
        System.out.println("Deleted Structure: " + deletedStructure);
    }

    @Test
    void testDeleteByProductId() {
        structureService.deleteByProductId(product.getManuCode());
        List<Structure> remainingStructures = structureRepository.findByProductId(product.getManuCode());
        System.out.println("Remaining Structures for Product ID " + product.getManuCode() + ": " + remainingStructures);
    }

    @Test
    void testFindByProductId() {
        List<StructureDTO> result = structureService.findByProductId(product.getManuCode());
        System.out.println("Structures for Product ID " + product.getManuCode() + ": " + result);
    }
}
