package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.entity.ProL;
import mit.iwrcore.IWRCore.entity.ProM;
import mit.iwrcore.IWRCore.entity.ProS;
import mit.iwrcore.IWRCore.repository.Pro.ProLCodeRepository;
import mit.iwrcore.IWRCore.repository.Pro.ProMCodeRepository;
import mit.iwrcore.IWRCore.repository.Pro.ProSCodeRepository;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProLDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProMDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProSDTO;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

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
        ProLDTO ldto=ProLDTO.builder().Lname("자전거").build();
        ProL proL =proCodeService.proLdtoToEntity(ldto);
        lCodeRepository.save(proL);

        ProLDTO ldto1=ProLDTO.builder().Lname("킥보드").build();
        ProL proL1 =proCodeService.proLdtoToEntity(ldto1);
        lCodeRepository.save(proL1);
    }
    // Mcode 삽입
    @Test
    @Transactional
    @Commit
    public void insertProMCodeTest(){
        ProMDTO mdto= ProMDTO.builder().Mname("전기자전거").proLDTO(proCodeService.findProL(1L)).build();
        ProM proM=proCodeService.proMdtoToEntity(mdto);
        mCodeRepository.save(proM);

        ProMDTO mdto1= ProMDTO.builder().Mname("일반자전거").proLDTO(proCodeService.findProL(1L)).build();
        ProM proM1=proCodeService.proMdtoToEntity(mdto1);
        mCodeRepository.save(proM1);

        ProMDTO mdto2= ProMDTO.builder().Mname("전기킥보드").proLDTO(proCodeService.findProL(2L)).build();
        ProM proM2=proCodeService.proMdtoToEntity(mdto2);
        mCodeRepository.save(proM2);

        ProMDTO mdto3= ProMDTO.builder().Mname("일반킥보드").proLDTO(proCodeService.findProL(2L)).build();
        ProM proM3=proCodeService.proMdtoToEntity(mdto3);
        mCodeRepository.save(proM3);
    }
    // Scode 삽입
    @Test
    @Transactional
    @Commit
    public void updateProSCodeTes11t(){
        ProSDTO sdto= ProSDTO.builder().Sname("전기자전거").proMDTO(proCodeService.findProM(1L)).build();
        ProS proS =proCodeService.proSdtoToEntity(sdto);
        sCodeRepository.save(proS);

        ProSDTO sdto1= ProSDTO.builder().Sname("도로자전거").proMDTO(proCodeService.findProM(2L)).build();
        ProS proS1 =proCodeService.proSdtoToEntity(sdto1);
        sCodeRepository.save(proS1);

        ProSDTO sdto2= ProSDTO.builder().Sname("산악자전거").proMDTO(proCodeService.findProM(2L)).build();
        ProS proS2 =proCodeService.proSdtoToEntity(sdto2);
        sCodeRepository.save(proS2);

        ProSDTO sdto3= ProSDTO.builder().Sname("전기킥보드").proMDTO(proCodeService.findProM(3L)).build();
        ProS proS3 =proCodeService.proSdtoToEntity(sdto3);
        sCodeRepository.save(proS3);

        ProSDTO sdto4= ProSDTO.builder().Sname("성인용킥보드").proMDTO(proCodeService.findProM(4L)).build();
        ProS proS4 =proCodeService.proSdtoToEntity(sdto4);
        sCodeRepository.save(proS4);

        ProSDTO sdto5= ProSDTO.builder().Sname("아동용킥보드").proMDTO(proCodeService.findProM(4L)).build();
        ProS proS5 =proCodeService.proSdtoToEntity(sdto5);
        sCodeRepository.save(proS5);
    }

    // Scode 삭제
    @Test
    public void deleteProSCodeTest(){ proCodeService.deleteProS(3L);
    }

    // L리스트 테스트
    @Test
    @Transactional
    @Commit
    public void test(){
        proCodeService.findListProL().forEach(System.out::println);
    }
    // S리스트 테스트
    @Test
    @Transactional
    @Commit
    public void selectboxProLTest(){
//        ProLDTO ldto=null;
        ProLDTO ldto=proCodeService.findProL(2L);
//        ProMDTO mdto=null;
        ProMDTO mdto=proCodeService.findProM(4L);
//        ProSDTO sdto=null;
        ProSDTO sdto=proCodeService.findProS(2L);
        proCodeService.findListProS(ldto, mdto, sdto).forEach(System.out::println);

//        try {
//            List<ProLDTO> result = proCodeService.findListProL(mdto, sdto);
//            if (result.isEmpty()) {
//                System.out.println("대분류 또는 중분류를 선택해주세요");
//            } else {
//                result.forEach(System.out::println);
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        } catch (Exception e) {
//            // 일반적인 예외 처리
//            System.out.println("An unexpected error occurred: " + e.getMessage());
//        }
    }
    // M리스트 테스트
    @Test
    @Transactional
    @Commit
    public void selectboxProMTest(){
//        ProLDTO ldto=null;
        ProLDTO ldto=proCodeService.findProL(2L);
//        ProMDTO mdto=null;
        ProMDTO mdto=proCodeService.findProM(4L);
//        ProSDTO sdto=null;
        ProSDTO sdto=proCodeService.findProS(2L);
        proCodeService.findListProM(ldto, mdto, sdto).forEach(System.out::println);
    }
    // All리스트 테스트
    @Test
    @Transactional
    @Commit
    public void allTest(){
//        ProLDTO ldto=null;
        ProLDTO ldto=proCodeService.findProL(2L);
//        ProMDTO mdto=null;
        ProMDTO mdto=proCodeService.findProM(4L);
//        ProSDTO sdto=null;
        ProSDTO sdto=proCodeService.findProS(2L);
        ProCodeListDTO list=proCodeService.findListProAll(ldto, mdto, sdto);
        list.getProLDTOs().forEach(System.out::println);
        list.getProMDTOs().forEach(System.out::println);
        list.getProSDTOs().forEach(System.out::println);
    }

}
