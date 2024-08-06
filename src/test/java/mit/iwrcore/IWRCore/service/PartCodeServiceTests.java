package mit.iwrcore.IWRCore.service;

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
        PartLDTO ldto=PartLDTO.builder().Lname("전기부품").build();
        PartL partL=partCodeService.partLdtoToEntity(ldto);
        lCodeRepository.save(partL);
    }

    // Mcode 삽입
    @Test
    @Transactional
    @Commit
    public void insertPartMCodeTest(){
        PartMDTO mdto= PartMDTO.builder().Mname("브레이크 시스템").partL(partCodeService.partLdtoToEntity(partCodeService.findPartL(1L))).build();
        PartM partM=partCodeService.partMdtoToEntity(mdto);
        mCodeRepository.save(partM);
    }
    // Scode 업데이트
    @Test
    @Transactional
    @Commit
    public void updatePartSCodeTes11t(){
        PartSDTO sdto= PartSDTO.builder().Sname("브레이크 캘리퍼 및 패드").partM(partCodeService.partMdtoToEntity(partCodeService.findPartM(5L))).build();
        PartS partS=partCodeService.partSdtoToEntity(sdto);
        sCodeRepository.save(partS);
    }

    // Scode 업데이트
    @Test
    @Transactional
    @Commit
    public void updatePartSCodeTest(){
        PartSDTO sdto= PartSDTO.builder().partScode(2L).Sname("프레임").partM(partCodeService.partMdtoToEntity(partCodeService.findPartM(1L))).build();
        PartS partS=partCodeService.partSdtoToEntity(sdto);
        sCodeRepository.save(partS);
    }
    // Scode 삭제
    @Test
    public void deletePartSCodeTest(){
        partCodeService.deletePartS(3L);
    }

    // Scode만 선택
    @Test
    @Transactional
    @Commit
    public void selectboxPartLTest(){
        PartSDTO sdto=null;
//        PartMDTO mdto=null;
        PartMDTO mdto=PartMDTO.builder().partMcode(1L).Mname("프레임 뭐시기").partL(partCodeService.partLdtoToEntity(partCodeService.findPartL(1L))).build();
//        PartSDTO sdto= PartSDTO.builder().partScode(2L).Sname("프레임이다").partM(partCodeService.partMdtoToEntity(partCodeService.findPartM(1L))).build();
        partCodeService.findListPartL(mdto, sdto).forEach(System.out::println);
    }

    @Test
    @Transactional
    @Commit
    public void selectboxPartMTest(){
        PartLDTO ldto=null;
//        PartSDTO sdto=null;
        PartSDTO sdto= PartSDTO.builder().partScode(2L).Sname("프레임이다").partM(partCodeService.partMdtoToEntity(partCodeService.findPartM(1L))).build();
        partCodeService.findListPartM(ldto, sdto).forEach(System.out::println);
    }

    @Test
    @Transactional
    @Commit
    public void selectboxPartSTest(){
        PartLDTO ldto=null;
//        PartMDTO mdto=null;
//        PartLDTO ldto= PartLDTO.builder().partLcode(1L).Lname("기계부품").build();
        PartMDTO mdto=PartMDTO.builder().partMcode(1L).Mname("프레임 뭐시기").partL(partCodeService.partLdtoToEntity(partCodeService.findPartL(1L))).build();

        partCodeService.findListPartS(ldto, mdto).forEach(System.out::println);
    }


}
