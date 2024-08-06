package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.entity.Box;
import mit.iwrcore.IWRCore.repository.BoxRepository;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;
import mit.iwrcore.IWRCore.security.service.BoxServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BoxServiceTests {
    @Autowired
    private BoxServiceImpl boxService;

    @Autowired
    private BoxRepository boxRepository;
    @Test
    public void testList() {
        // 테스트 데이터를 데이터베이스에 저장
        Box box1 = Box.builder().boxCode(1L).boxName("Box 1").build();
        Box box2 = Box.builder().boxCode(2L).boxName("Box 2").build();
        Box box3 = Box.builder().boxCode(3L).boxName("Box 3").build();
        boxRepository.save(box1);
        boxRepository.save(box2);
        boxRepository.save(box3);

    }
}
