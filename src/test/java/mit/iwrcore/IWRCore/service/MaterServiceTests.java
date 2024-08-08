package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
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
        MaterLDTO ldto=MaterLDTO.builder().lname("프레임").build();
        MaterL ML=materService.materLdtoToEntity(ldto);
        MLRepository.save(ML);

        MaterLDTO ldto1=MaterLDTO.builder().lname("서스펜션시스템").build();
        MaterL ML2=materService.materLdtoToEntity(ldto1);
        MLRepository.save(ML2);

        MaterLDTO ldto3=MaterLDTO.builder().lname("브레이크시스템").build();
        MaterL ML3=materService.materLdtoToEntity(ldto3);
        MLRepository.save(ML3);
    }
    // M 삽입
    @Test
    @Transactional
    @Commit
    public void insertMTest(){
        MaterMDTO mdto= MaterMDTO.builder().Mname("프레임").materLDTO(materService.findMaterL(1L)).build();
        MaterM MM=materService.materMdtoToEntity(mdto);
        MMRepository.save(MM);

        MaterMDTO mdto1= MaterMDTO.builder().Mname("서스펜션 유닛").materLDTO(materService.findMaterL(2L)).build();
        MaterM MM1=materService.materMdtoToEntity(mdto1);
        MMRepository.save(MM1);

        MaterMDTO mdto2= MaterMDTO.builder().Mname("서스펜션 부품").materLDTO(materService.findMaterL(2L)).build();
        MaterM MM2=materService.materMdtoToEntity(mdto2);
        MMRepository.save(MM2);

        MaterMDTO mdto3= MaterMDTO.builder().Mname("브레이크레버").materLDTO(materService.findMaterL(3L)).build();
        MaterM MM3=materService.materMdtoToEntity(mdto3);
        MMRepository.save(MM3);

        MaterMDTO mdto4= MaterMDTO.builder().Mname("브레이크 와이어").materLDTO(materService.findMaterL(3L)).build();
        MaterM MM4=materService.materMdtoToEntity(mdto4);
        MMRepository.save(MM4);
    }
    // S 삽입
    @Test
    @Transactional
    @Commit
    public void updatePartSCodeTes11t(){
        MaterSDTO sdto= MaterSDTO.builder().Sname("메탈프레임").materMDTO(materService.findMaterM(1L)).build();
        MaterS MS=materService.materSdtoToEntity(sdto);
        MSRepository.save(MS);

        MaterSDTO sdto1= MaterSDTO.builder().Sname("알루미늄프레임").materMDTO(materService.findMaterM(1L)).build();
        MaterS MS1=materService.materSdtoToEntity(sdto1);
        MSRepository.save(MS1);

        MaterSDTO sdto2= MaterSDTO.builder().Sname("메탈 서스펜션유닛").materMDTO(materService.findMaterM(2L)).build();
        MaterS MS2=materService.materSdtoToEntity(sdto2);
        MSRepository.save(MS2);

        MaterSDTO sdto3= MaterSDTO.builder().Sname("알루미늄 서스펜션 부품").materMDTO(materService.findMaterM(3L)).build();
        MaterS MS3=materService.materSdtoToEntity(sdto3);
        MSRepository.save(MS3);

        MaterSDTO sdto4= MaterSDTO.builder().Sname("플라스틱 브레이크 레버").materMDTO(materService.findMaterM(4L)).build();
        MaterS MS4=materService.materSdtoToEntity(sdto4);
        MSRepository.save(MS4);

        MaterSDTO sdto5= MaterSDTO.builder().Sname("메탈 브레이크 레버").materMDTO(materService.findMaterM(4L)).build();
        MaterS MS5=materService.materSdtoToEntity(sdto5);
        MSRepository.save(MS5);

        MaterSDTO sdto6= MaterSDTO.builder().Sname("경량 브레이크 와이어").materMDTO(materService.findMaterM(5L)).build();
        MaterS MS6=materService.materSdtoToEntity(sdto6);
        MSRepository.save(MS6);
    }

    // S 업데이트
    @Test
    @Transactional
    @Commit
    public void updateSTest(){
        MaterSDTO sdto= MaterSDTO.builder().materScode(3L).Sname("소형 서스펜션유닛").materMDTO(materService.findMaterM(2L)).build();
        MaterS materS=materService.materSdtoToEntity(sdto);
        MSRepository.save(materS);
    }
    // S 삭제
    @Test
    public void deletePartSCodeTest(){
        materService.deleteMaterS(1L);
    }

    // L리스트 테스트
    @Test
    @Transactional
    @Commit
    public void selectboxLTest(){
        materService.findListMaterL().forEach(System.out::println);
    }
    // M리스트 테스트
    @Test
    @Transactional
    @Commit
    public void selectboxMTest(){
//        MaterLDTO ldto=null;
        MaterLDTO ldto=materService.findMaterL(1L);
//        MaterMDTO mdto=null;
        MaterMDTO mdto=materService.findMaterM(2L);
//        MaterSDTO sdto=null;
        MaterSDTO sdto=materService.findMaterS(3L);
        materService.findListMaterM(ldto, mdto, sdto).forEach(System.out::println);
    }
    // S리스트 테스트
    @Test
    @Transactional
    @Commit
    public void selectboxSTest(){
//        MaterLDTO ldto=null;
        MaterLDTO ldto=materService.findMaterL(1L);
//        MaterMDTO mdto=null;
        MaterMDTO mdto=materService.findMaterM(2L);
//        MaterSDTO sdto=null;
        MaterSDTO sdto=materService.findMaterS(5L);
        materService.findListMaterS(ldto, mdto, sdto).forEach(System.out::println);
    }
    // ALL 리스트 테스트
    @Test
    @Transactional
    @Commit
    public void allList(){
        //        MaterLDTO ldto=null;
        MaterLDTO ldto=materService.findMaterL(1L);
//        MaterMDTO mdto=null;
        MaterMDTO mdto=materService.findMaterM(2L);
//        MaterSDTO sdto=null;
        MaterSDTO sdto=materService.findMaterS(5L);
        MaterCodeListDTO dto=materService.findListMaterAll(ldto, mdto, sdto);
        dto.getMaterLDTOs().forEach(System.out::println);
        dto.getMaterMDTOs().forEach(System.out::println);
        dto.getMaterSDTOs().forEach(System.out::println);
    }


}
