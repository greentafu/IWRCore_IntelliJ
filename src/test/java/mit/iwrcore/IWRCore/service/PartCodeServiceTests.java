package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.dto.PartCodeListDTO;
import mit.iwrcore.IWRCore.entity.PartL;
import mit.iwrcore.IWRCore.entity.PartM;
import mit.iwrcore.IWRCore.entity.PartS;
import mit.iwrcore.IWRCore.repository.PartLCodeRepository;
import mit.iwrcore.IWRCore.repository.PartMCodeRepository;
import mit.iwrcore.IWRCore.repository.PartSCodeRepository;
import mit.iwrcore.IWRCore.security.dto.PartLDTO;
import mit.iwrcore.IWRCore.security.dto.PartMDTO;
import mit.iwrcore.IWRCore.security.dto.PartSDTO;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PartCodeServiceTests {
    @Autowired
    private PartCodeService partCodeService;

    @Autowired
    private PartLCodeRepository lCodeRepository;
    @Autowired
    private PartMCodeRepository mCodeRepository;
    @Autowired
    private PartSCodeRepository sCodeRepository;

    // Lcode 삽입
    @Test
    public void insertPartLCodeTest(){
        PartLDTO ldto=PartLDTO.builder()
                .Lname("전기부품")
                .build();
        PartL partL=partCodeService.partLdtoToEntity(ldto);
        lCodeRepository.save(partL);
    }
    // Mcode 삽입
    @Test
    @Transactional
    @Commit
    public void insertPartMCodeTest(){
        PartMDTO mdto= PartMDTO.builder()
                .Mname("충전 시스템")
                .partLDTO(partCodeService.findPartL(2L))
                .build();
        PartM partM=partCodeService.partMdtoToEntity(mdto);
        mCodeRepository.save(partM);
    }
    // Scode 삽입
    @Test
    @Transactional
    @Commit
    public void updatePartSCodeTes11t(){
        PartSDTO sdto= PartSDTO.builder()
                .Sname("충전기 코드")
                .partMDTO(partCodeService.findPartM(3L))
                .build();
        PartS partS=partCodeService.partSdtoToEntity(sdto);
        sCodeRepository.save(partS);
    }

    // Scode 업데이트
    @Test
    @Transactional
    @Commit
    public void updatePartSCodeTest(){
        PartSDTO sdto= PartSDTO.builder()
                .partScode(4L)
                .Sname("충전기 코드 수정")
                .partMDTO(partCodeService.findPartM(3L))
                .build();
        PartS partS=partCodeService.partSdtoToEntity(sdto);
        sCodeRepository.save(partS);
    }
    // Scode 삭제
    @Test
    public void deletePartSCodeTest(){
        partCodeService.deletePartS(4L);
    }

    // LDTO 리스트 테스트
    @Test
    @Transactional
    @Commit
    public void selectboxPartLTest(){
        PartSDTO sdto=null;
        PartMDTO mdto=null;
//        PartMDTO mdto=PartMDTO.builder().partMcode(3L).Mname("프레임 뭐시기").partLDTO(partCodeService.findPartL(2L)).build();
//        PartSDTO sdto= PartSDTO.builder().partScode(2L).Sname("프레임").partMDTO(partCodeService.findPartM(1L)).build();
        partCodeService.findListPartL().forEach(System.out::println);
    }
    // MDTO 리스트 테스트
    @Test
    @Transactional
    @Commit
    public void selectboxPartMTest(){
        PartLDTO ldto=null;
        PartMDTO mdto=null;
        PartSDTO sdto=null;
//        PartLDTO ldto=PartLDTO.builder().partLcode(1L).Lname("기계부품").build();
//        PartSDTO sdto= PartSDTO.builder().partScode(2L).Sname("프레임").partMDTO(partCodeService.findPartM(1L)).build();
        partCodeService.findListPartM(ldto, mdto, sdto).forEach(System.out::println);
    }
    // SDTO 리스트 테스트
    @Test
    @Transactional
    @Commit
    public void selectboxPartSTest(){
        PartSDTO sdto=null;
        PartLDTO ldto=null;
        PartMDTO mdto=null;
//        PartLDTO ldto= PartLDTO.builder().partLcode(1L).Lname("기계부품").build();
//        PartMDTO mdto=PartMDTO.builder().partMcode(1L).Mname("프레임 시스템").partLDTO(partCodeService.findPartL(1L)).build();
        partCodeService.findListPartS(ldto, mdto, sdto).forEach(System.out::println);
    }
    // ALLDTO 리스트 테스트
    @Test
    @Transactional
    @Commit
    public void dtoalllist(){
//        PartLDTO ldto= PartLDTO.builder().partLcode(1L).Lname("기계부품").build();
//        PartMDTO mdto=PartMDTO.builder().partMcode(1L).Mname("프레임 시스템").partLDTO(partCodeService.findPartL(1L)).build();
//        PartSDTO sdto= PartSDTO.builder().partScode(2L).Sname("프레임").partMDTO(partCodeService.findPartM(1L)).build();
        PartLDTO ldto=null;
        PartMDTO mdto=null;
        PartSDTO sdto=null;
        PartCodeListDTO list=partCodeService.findListPartAll(ldto, mdto, sdto);
        list.getPartLDTOs().forEach(System.out::println);
        list.getPartMDTOs().forEach(System.out::println);
        list.getPartSDTOs().forEach(System.out::println);
    }


}
