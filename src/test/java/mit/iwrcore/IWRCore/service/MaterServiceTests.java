package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.Mater.MaterLRepository;
import mit.iwrcore.IWRCore.repository.Mater.MaterMRepository;
import mit.iwrcore.IWRCore.repository.Mater.MaterSRepository;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterLDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterMDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;
import mit.iwrcore.IWRCore.security.service.MaterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MaterServiceTests {
    @Autowired
    private MaterService materService;

    @Autowired
    private MaterLRepository MLRepository;
    @Autowired
    private MaterMRepository MMRepository;
    @Autowired
    private MaterSRepository MSRepository;

    // L 삽입
    @Test
    public void insertLTest(){
        MaterLDTO ldto=MaterLDTO.builder().lname("AAA").build();
        MaterL ML=materService.materLdtoToEntity(ldto);
        MLRepository.save(ML);

    }

    // M 삽입
    @Test
    @Transactional
    @Commit
    public void insertMTest(){
        MaterMDTO mdto= MaterMDTO.builder().Mname("aaa").materL(materService.materLdtoToEntity(materService.findMaterL(10L))).build();
        MaterM MM=materService.materMdtoToEntity(mdto);
        MMRepository.save(MM);
    }
    // S 삽입
    @Test
    @Transactional
    @Commit
    public void updatePartSCodeTes11t(){
        MaterSDTO sdto= MaterSDTO.builder().Sname("브레이크 ").materm(materService.materMdtoToEntity(materService.findMaterM(13L))).build();
        MaterS MS=materService.materSdtoToEntity(sdto);
        MSRepository.save(MS);
    }

    // S 업데이트
    @Test
    @Transactional
    @Commit
    public void updateSTest(){
        MaterSDTO sdto= MaterSDTO.builder().materScode(6L).Sname("프레임").materm(materService.materMdtoToEntity(materService.findMaterM(13L))).build();
        MaterS materS=materService.materSdtoToEntity(sdto);
        MSRepository.save(materS);
    }
    // S 삭제
    @Test
    public void deletePartSCodeTest(){
        materService.deleteMaterS(1L);
    }

    // S만 선택
    @Test
    @Transactional
    @Commit
    public void selectboxLTest(){
        MaterSDTO sdto=null;
//        MaterMDTO mdto=null;
        MaterMDTO mdto=MaterMDTO.builder().materMcode(13L).Mname("프레임 뭐시기").materL(materService.materLdtoToEntity(materService.findMaterL(10L))).build();
//        MaterSDTO sdto= MaterSDTO.builder().materScode(2L).Sname("프레임이다").materM(materService.MaterMDTOToEntity(materService.findMaterM(1L))).build();
        materService.findListPartL(mdto, sdto).forEach(System.out::println);
    }

    @Test
    @Transactional
    @Commit
    public void selectboxMTest(){
        MaterLDTO ldto=null;
//        MaterSDTO sdto=null;
        MaterSDTO sdto= MaterSDTO.builder().materScode(6L).Sname("프레임이다").materm(materService.materMdtoToEntity(materService.findMaterM(13L))).build();
        materService.findListPartM(ldto, sdto).forEach(System.out::println);
    }

    @Test
    @Transactional
    @Commit
    public void selectboxSTest(){
 //       MaterLDTO ldto=null;
 //       MaterMDTO mdto=null;
        MaterLDTO ldto= MaterLDTO.builder().materLcode(10L).lname("AAA").build();
        MaterMDTO mdto=MaterMDTO.builder().materMcode(13L).Mname("브레이크 시스템").materL(materService.materLdtoToEntity(materService.findMaterL(10L))).build();

        materService.findListPartS(ldto, mdto).forEach(System.out::println);
    }


}
