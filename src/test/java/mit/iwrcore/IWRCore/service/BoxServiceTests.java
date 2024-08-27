package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Box;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.repository.BoxRepository;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.security.service.BoxServiceImpl;
import mit.iwrcore.IWRCore.security.service.MaterialService;
import mit.iwrcore.IWRCore.security.service.MemberService;
import mit.iwrcore.IWRCore.security.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
public class BoxServiceTests {
    @Autowired
    private BoxServiceImpl boxService;

    @Autowired
    private BoxRepository boxRepository;

    @Autowired
    private ProductService productService;
    @Test
    @Transactional
    @Commit
    public void test(){
        ProductDTO productDTO=productService.getProductById(1L);
        System.out.println("###"+productDTO);
        System.out.println("@@@"+productService.productDtoToEntity(productDTO));
    }


    @Test
    public void testList() {
        // 테스트 데이터를 데이터베이스에 저장
        Box box1 = Box.builder().boxCode(1L).boxName("A창고").build();
        Box box2 = Box.builder().boxCode(2L).boxName("B창고").build();
        Box box3 = Box.builder().boxCode(3L).boxName("C창고").build();
        boxRepository.save(box1);
        boxRepository.save(box2);
        boxRepository.save(box3);
    }

    @Test
    @Transactional
    @Commit
    public void listbox(){
        System.out.println("############"+boxService.list());
    }

}
