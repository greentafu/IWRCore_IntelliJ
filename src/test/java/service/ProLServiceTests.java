package service;

import mit.iwrcore.IWRCore.dto.ProLDTO;
import mit.iwrcore.IWRCore.service.ProLService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class ProLServiceTests {

    @Autowired
    private ProLService service;

    @Test
    public void testRegister() {
         ProLDTO proLDTO=ProLDTO.builder()
                .manuLcode(12345L)
                .Lname("Sameple name")
                .build();
        System.out.println(service.register(proLDTO));


    }

}
