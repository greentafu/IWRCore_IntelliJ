package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.security.dto.ProLDTO;
import mit.iwrcore.IWRCore.service.ProLService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProLServiceTests {

    @Autowired
    private ProLService service;

    @Test
    public void testRegister() {
        ProLDTO pr = ProLDTO.builder()
                .manuLcode(12345L)
                .Lname("자전거")
                .build();
        System.out.println(service.register(pr));


    }

}