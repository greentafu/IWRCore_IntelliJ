package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.repository.BoxRepository;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.service.MaterialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MaterialServiceTests {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private BoxRepository boxRepository;


    @Test
    public void insertTest(){
        MaterialDTO dto=MaterialDTO.builder().name("abc").build();
        Material material=materialService.materEntity(dto);
        materialRepository.save(material);
    }
}
