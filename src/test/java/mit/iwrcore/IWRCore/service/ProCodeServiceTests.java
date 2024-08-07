package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.entity.ProL;
import mit.iwrcore.IWRCore.entity.ProM;
import mit.iwrcore.IWRCore.entity.ProS;
import mit.iwrcore.IWRCore.repository.ProLCodeRepository;
import mit.iwrcore.IWRCore.repository.ProMCodeRepository;
import mit.iwrcore.IWRCore.repository.ProSCodeRepository;
import mit.iwrcore.IWRCore.security.dto.ProLDTO;
import mit.iwrcore.IWRCore.security.dto.ProMDTO;
import mit.iwrcore.IWRCore.security.dto.ProSDTO;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ProCodeServiceTests {
    @Autowired
    private ProCodeService proCodeService;
    @Autowired
    private ProLCodeRepository lCodeRepository;
    @Autowired
    private ProMCodeRepository mCodeRepository;
    @Autowired
    private ProSCodeRepository sCodeRepository;

    // Lcode 삽입
    @Test
    public void insertProLCodeTest(){
        ProLDTO ldto=ProLDTO.builder().Lname("전기부품").build();
        ProL proL =proCodeService.proLdtoToEntity(ldto);
        lCodeRepository.save(proL);
    }

    // Mcode 삽입
    @Test
    @Transactional
    @Commit
    public void insertProMCodeTest(){
        ProMDTO mdto= ProMDTO.builder().Mname("브레이크 시스템").proL(proCodeService.proLdtoToEntity(proCodeService.findProL(14L))).build();
        ProM proM=proCodeService.proMdtoToEntity(mdto);
        mCodeRepository.save(proM);
    }
    // Scode 업데이트
    @Test
    @Transactional
    @Commit
    public void updateProSCodeTes11t(){
        ProSDTO sdto= ProSDTO.builder().Sname("브레이크 캘리퍼 및 패드").proM(proCodeService.proMdtoToEntity(proCodeService.findProM(3L))).build();
        ProS proS =proCodeService.proSdtoToEntity(sdto);
        sCodeRepository.save(proS);
    }

    // Scode 업데이트
    @Test
    @Transactional
    @Commit
    public void updateProSCodeTest(){
        ProSDTO sdto= ProSDTO.builder().proScode(2L).Sname("프레임").proM(proCodeService.proMdtoToEntity(proCodeService.findProM(3L))).build();
        ProS proS =proCodeService.proSdtoToEntity(sdto);
        sCodeRepository.save(proS);
    }
    // Scode 삭제
    @Test
    public void deleteProSCodeTest(){ proCodeService.deleteProS(3L);
    }

    // Scode만 선택
    // Scode만 선택
    @Test
    @Transactional
    @Commit
    public void selectboxProLTest(){
//        PartMDTO mdto=null;
        ProMDTO mdto=ProMDTO.builder().proMcode(3L).Mname("브레이크 시스템").proL(proCodeService.proLdtoToEntity(proCodeService.findProL(14L))).build();
//        ProSDTO sdto= ProSDTO.builder().proScode(2L).Sname("프레임이다").proM(proCodeService.proMdtoToEntity(proCodeService.findProM(1L))).build();
        ProSDTO sdto = null;
        try {
            List<ProLDTO> result = proCodeService.findListProL(mdto, sdto);
            if (result.isEmpty()) {
                System.out.println("대분류 또는 중분류를 선택해주세요");
            } else {
                result.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // 일반적인 예외 처리
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    @Test
    @Transactional
    @Commit
    public void selectboxProMTest(){
        ProLDTO ldto=null;
//        ProSDTO sdto=null;
        ProSDTO sdto= ProSDTO.builder().proScode(3L).Sname("프레임이다").proM(proCodeService.proMdtoToEntity(proCodeService.findProM(3L))).build();
        proCodeService.findListProM(ldto, sdto).forEach(System.out::println);
    }

    @Test
    @Transactional
    @Commit
    public void selectboxProSTest(){
        ProLDTO ldto=null;
//        ProMDTO mdto=null;
//        ProLDTO ldto= ProLDTO.builder().proLcode(1L).Lname("기계부품").build();
        ProMDTO mdto=ProMDTO.builder().proMcode(1L).Mname("프레임 뭐시기").proL(proCodeService.proLdtoToEntity(proCodeService.findProL(14L))).build();

        proCodeService.findListProS(ldto, mdto).forEach(System.out::println);
    }


}
