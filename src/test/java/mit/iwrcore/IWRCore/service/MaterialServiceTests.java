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
import mit.iwrcore.IWRCore.security.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private MemberService memberService;
    @Autowired
    private MaterService materService;
    @Autowired
    private BoxService boxService;

    @Autowired
    private MaterialServiceImpl materialServiceImpl;

    @Test
    @Transactional
    @Commit
    public void insertMaterial(){
        BoxDTO box=BoxDTO.builder().boxname("Box 1").boxcode(1L).build();
        MaterialDTO materialDTO=MaterialDTO.builder()
                .name("앞바퀴")
                .unit("")
                .standard("")
                .color("빨강")
                .file("")
                .memberDTO(memberService.findMemberDto(1L, null))
                .materSDTO(materService.findMaterS(1L))
                .boxDTO(box)
                .build();
        materialRepository.save(materialServiceImpl.materdtoToEntity(materialDTO));
        MaterialDTO materialDTO1=MaterialDTO.builder()
                .name("뒷바퀴")
                .unit("")
                .standard("")
                .color("검정")
                .file("")
                .memberDTO(memberService.findMemberDto(2L, null))
                .materSDTO(materService.findMaterS(2L))
                .boxDTO(box)
                .build();
        materialRepository.save(materialServiceImpl.materdtoToEntity(materialDTO1));
        MaterialDTO materialDTO2=MaterialDTO.builder()
                .name("브레이크")
                .unit("")
                .standard("")
                .color("회색")
                .file("")
                .memberDTO(memberService.findMemberDto(3L, null))
                .materSDTO(materService.findMaterS(3L))
                .boxDTO(box)
                .build();
        materialRepository.save(materialServiceImpl.materdtoToEntity(materialDTO2));
    }
    @Test
    @Transactional
    @Commit
    public void list(){
        Pageable pageable= PageRequest.of(0,2);
        materialRepository.materialList(pageable).forEach(System.out::println);
    }
    @Test
    @Transactional
    @Commit
    public void listTest(){
        materialRepository.materialListPart(null,null).forEach(System.out::println);
    }
    @Test
    @Transactional
    @Commit
    public void deleteTest(){
        materialService.deleteJa(6L);
    }
    @Test
    @Transactional
    @Commit
    public void insetTest1(){
        BoxDTO box=BoxDTO.builder().boxname("Box 1").boxcode(1L).build();
        MaterialDTO materialDTO2=MaterialDTO.builder()
                .name("브레이크")
                .unit("")
                .standard("")
                .color("회색")
                .file("")
                .memberDTO(memberService.findMemberDto(3L, null))
                .materSDTO(materService.findMaterS(3L))
                .boxDTO(box)
                .build();
        materialService.insertj(materialDTO2);
    }
//
//
//    @Test
//    void insertj_shouldSaveMaterial() {
//        // 테스트에 사용할 BoxDTO 객체 생성
//        BoxDTO boxDTO = BoxDTO.builder().boxcode(1L).boxname("Test Box").build();
//        Box box = Box.builder().boxCode(1L).boxName("Test Box").build();
//        boxRepository.save(box);
//
//        // 테스트에 사용할 MaterSDTO 객체 생성
//        MaterSDTO materSDTO = MaterSDTO.builder().materScode(1L).Sname("sname").build();
//        MaterS materS = MaterS.builder().materScode(1L).Sname("sname").build();
//        materSRepository.save(materS);
//
//        // 테스트할 MaterialDTO 객체 생성
//        MaterialDTO dto = MaterialDTO.builder()
//                .materCode(1L)
//                .name("Test Material")
//                .unit("Unit")
//                .Standard("Standard")
//                .color("Color")
//                .file("File")
//                .boxDTO(boxDTO)
//                .materSDTO(materSDTO)
//                .build();
//
//        // insertj 메서드 호출
//        materialService.insertj(dto);
//    }
//
//    @Test
//    void insertbox_shouldSaveBox() {
//        // 테스트할 BoxDTO 객체 생성
//        BoxDTO boxDTO = BoxDTO.builder().boxcode(2L).boxname("Test Box2").build();
//        Box box = Box.builder().boxCode(2L).boxName("Test Box2").build();
//        boxRepository.save(box);
//
//
//    }
//    @Test
//    void insertsmater_shouldSaveMaterS() {
//        // 테스트할 MaterSDTO 객체 생성
//        MaterSDTO materSDTO2 = MaterSDTO.builder().materScode(2L).Sname("sname").build();
//        MaterS materS = MaterS.builder().materScode(2L).Sname("sname").build();
//        materSRepository.save(materS);
//
//
//    }
//    @Test
//    void deleteJa_shouldDeleteMaterial() {
//        // 테스트에 사용할 Box 및 MaterS 객체 생성 및 저장
//        Box box = Box.builder().boxCode(1L).boxName("Test Box").build();
//        boxRepository.save(box);
//
//        MaterSDTO materSDTO2 = MaterSDTO.builder().materScode(1L).Sname("sname").build();
//        MaterS materS = MaterS.builder().materScode(1L).Sname("sname").build();
//        materSRepository.save(materS);
//
//        // 테스트할 자재 객체 생성 및 저장
//        Material material = Material.builder()
//                .materCode(1L)
//                .name("Test Material")
//                .box(box)
//                .materS(materS)
//                .build();
//        materialRepository.save(material);
//
//        // deleteJa 메서드 호출
//        materialService.deleteJa(1L);
//
//
//    }
//
//    // 자재 찾기 기능을 테스트합니다.
//    @Test
//    @Commit
//    @Transactional
//    void findM(){
//        Long testMaterCode=1L;
//        MaterialDTO result = materialService.findM(testMaterCode);
//        System.out.println("MaterialDTO result: " + result);
//    }
//    @Test
//    @Commit
//    @Transactional
//    // 창고별 자재 목록 조회 기능을 테스트합니다.
//    void findB(){
//        BoxDTO boxDTO = BoxDTO.builder().boxcode(1L).build();
//        List<MaterialDTO> result = materialService.BfindList(boxDTO);
//    }
//    // 소분류별 자재 목록 조회 기능을 테스트합니다.
//    @Test
//    @Commit
//    @Transactional
//    void findsM(){
//        MaterSDTO materSDTO = MaterSDTO.builder().materScode(1L).build();
//        List<MaterialDTO> result = materialService.MfindList(materSDTO);
//
//    }
}
