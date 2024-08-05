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
        PartLDTO ldto=PartLDTO.builder().Lname("기계부품").build();
        PartL partL=partCodeService.partLdtoToEntity(ldto);
        lCodeRepository.save(partL);
    }

    // Mcode 삽입
    @Test
    public void insertPartMCodeTest(){
        PartMDTO mdto= PartMDTO.builder().Mname("프레임 및 관련 부품").partL(partCodeService.findPartL(1L)).build();
        PartM partM=partCodeService.partMdtoToEntity(mdto);
        mCodeRepository.save(partM);
    }

    // Scode 삽입
    @Test
    public void insertPartSCodeTest(){
        PartSDTO sdto= PartSDTO.builder().Sname("프레임").partM(partCodeService.findPartM(1L)).build();
        PartS partS=partCodeService.partSdtoToEntity(sdto);
        sCodeRepository.save(partS);
    }

    // Scode 업데이트
    @Test
    public void updatePartSCodeTest(){
        PartSDTO sdto= PartSDTO.builder().partScode(1L).Sname("프레임이다").partM(partCodeService.findPartM(1L)).build();
        PartS partS=partCodeService.partSdtoToEntity(sdto);
        sCodeRepository.save(partS);
    }
    // Scode 삭제
    @Test
    public void deletePartSCodeTest(){
        partCodeService.deletePartS(1L);
    }

    // Scode만 선택
    @Test
    public void selectboxPartSTest(){

    }

}
