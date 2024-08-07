package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Box;
import mit.iwrcore.IWRCore.entity.MaterS;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.repository.BoxRepository;
import mit.iwrcore.IWRCore.repository.Mater.MaterSRepository;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.service.MaterialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
public class MaterialServiceTests {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private BoxRepository boxRepository;
    @Autowired
    private MaterSRepository materSRepository;


    @Test
    void insertj_shouldSaveMaterial() {
        // 테스트에 사용할 BoxDTO 객체 생성
        BoxDTO boxDTO = BoxDTO.builder().boxcode(1L).boxname("Test Box").build();
        Box box = Box.builder().boxCode(1L).boxName("Test Box").build();
        boxRepository.save(box);

        // 테스트에 사용할 MaterSDTO 객체 생성
        MaterSDTO materSDTO = MaterSDTO.builder().materScode(1L).Sname("sname").build();
        MaterS materS = MaterS.builder().materScode(1L).Sname("sname").build();
        materSRepository.save(materS);

        // 테스트할 MaterialDTO 객체 생성
        MaterialDTO dto = MaterialDTO.builder()
                .materCode(1L)
                .name("Test Material")
                .unit("Unit")
                .Standard("Standard")
                .color("Color")
                .file("File")
                .boxDTO(boxDTO)
                .materSDTO(materSDTO)
                .build();

        // insertj 메서드 호출
        materialService.insertj(dto);
    }

    @Test
    void insertbox_shouldSaveBox() {
        // 테스트할 BoxDTO 객체 생성
        BoxDTO boxDTO = BoxDTO.builder().boxcode(2L).boxname("Test Box2").build();
        Box box = Box.builder().boxCode(2L).boxName("Test Box2").build();
        boxRepository.save(box);


    }
    @Test
    void insertsmater_shouldSaveMaterS() {
        // 테스트할 MaterSDTO 객체 생성
        MaterSDTO materSDTO2 = MaterSDTO.builder().materScode(2L).Sname("sname").build();
        MaterS materS = MaterS.builder().materScode(2L).Sname("sname").build();
        materSRepository.save(materS);


    }
    @Test
    void deleteJa_shouldDeleteMaterial() {
        // 테스트에 사용할 Box 및 MaterS 객체 생성 및 저장
        Box box = Box.builder().boxCode(1L).boxName("Test Box").build();
        boxRepository.save(box);

        MaterSDTO materSDTO2 = MaterSDTO.builder().materScode(1L).Sname("sname").build();
        MaterS materS = MaterS.builder().materScode(1L).Sname("sname").build();
        materSRepository.save(materS);

        // 테스트할 자재 객체 생성 및 저장
        Material material = Material.builder()
                .materCode(1L)
                .name("Test Material")
                .box(box)
                .materS(materS)
                .build();
        materialRepository.save(material);

        // deleteJa 메서드 호출
        materialService.deleteJa(1L);


    }

    // 자재 찾기 기능을 테스트합니다.
    @Test
    @Commit
    @Transactional
    void findM(){
        Long testMaterCode=1L;
        MaterialDTO result = materialService.findM(testMaterCode);
        System.out.println("MaterialDTO result: " + result);
    }
    @Test
    @Commit
    @Transactional
    // 창고별 자재 목록 조회 기능을 테스트합니다.
    void findB(){
        BoxDTO boxDTO = BoxDTO.builder().boxcode(1L).build();
        List<MaterialDTO> result = materialService.BfindList(boxDTO);
    }
    // 소분류별 자재 목록 조회 기능을 테스트합니다.
    @Test
    @Commit
    @Transactional
    void findsM(){
        MaterSDTO materSDTO = MaterSDTO.builder().materScode(1L).build();
        List<MaterialDTO> result = materialService.MfindList(materSDTO);

    }
}
