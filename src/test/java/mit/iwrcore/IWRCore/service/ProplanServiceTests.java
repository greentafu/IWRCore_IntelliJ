package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.repository.ProplanRepository;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import mit.iwrcore.IWRCore.security.service.ProplanServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ProplanServiceTests {

    @Autowired
    private ProplanRepository proplanRepository; // 실제 ProplanRepository 빈 주입

    @Autowired
    private ProductRepository productRepository; // 실제 ProductRepository 빈 주입

    @Autowired
    private MemberRepository memberRepository; // 실제 MemberRepository 빈 주입

    @Autowired
    private ProplanServiceImpl proplanService; // 실제 ProplanServiceImpl 빈 주입

    @Test
    public void testSave() {
        // 테스트에 사용할 DTO 객체 생성
        ProplanDTO dto = new ProplanDTO();
        dto.setProplanNo(1L);
        dto.setPronum(100L);
        dto.setFilename("file.txt");
        dto.setStartDate(LocalDateTime.now());
        dto.setEndDate(LocalDateTime.now().plusDays(1));
        dto.setLine("Line1");
        dto.setDetails("Details");
        dto.setProductId(1L); // 실제 Product ID 설정
        dto.setWriterId(1L); // 실제 Member ID 설정

        // DTO를 Entity로 변환하여 저장
        proplanService.save(dto);
    }
}
