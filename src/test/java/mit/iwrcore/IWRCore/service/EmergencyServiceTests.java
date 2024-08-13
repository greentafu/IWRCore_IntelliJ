package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.entity.Balju;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.repository.BaljuRepository;
import mit.iwrcore.IWRCore.repository.EmergencyRepository;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.security.dto.EmergencyDTO;
import mit.iwrcore.IWRCore.security.service.EmergencyServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@Nested
@SpringBootTest
class EmergencyServiceTests {

    @Autowired
    private EmergencyServiceImpl emergencyimpl;

    @Autowired
    private EmergencyRepository emergencyRepository;

    @Autowired
    private BaljuRepository baljuRepository;

    @Autowired
    private MemberRepository memberRepository;


//    @Test
//    public void testFindById() {
//        // Arrange
//        Emergency emergency = Emergency.builder()
//                .emerNo(1L)
//                .emerNum(10L)
//                .emerDate(LocalDateTime.now())
//                .who("John Doe")
//                .emcheck(1L)
//                .build();
//
//
//        // No assertions, just check if the result is present
//        System.out.println("Found EmergencyDTO: " + result);
//    }
//
//    @Test
//    public void testFindAll() {
//        // Arrange
//        Emergency emergency = Emergency.builder()
//                .emerNo(1L)
//                .emerNum(10L)
//                .emerDate(LocalDateTime.now())
//                .who("John Doe")
//                .emcheck(1L)
//                .build();
//        emergencyRepository.save(emergency);
//
//        // Act
//        List<EmergencyDTO> result = emergencyimpl.findAll();
//
//        // No assertions, just check the size of the result
//        System.out.println("All EmergencyDTOs: " + result);
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Arrange
//        Emergency emergency = Emergency.builder()
//                .emerNo(1L)
//                .emerNum(10L)
//                .emerDate(LocalDateTime.now())
//                .who("John Doe")
//                .emcheck(1L)
//                .build();
//        emergencyRepository.save(emergency);
//
//        // Act
//        emergencyimpl.deleteById(1L);
//
//        // No assertions, just check if the item is deleted
//        System.out.println("Deleted EmergencyDTO with ID 1");
//    }
//
//    @Test
//    public void testFindByBalju() {
//        // Arrange
//        Balju balju = Balju.builder().baljuNo(1L).build();
//        baljuRepository.save(balju);
//
//        Emergency emergency = Emergency.builder()
//                .emerNo(1L)
//                .emerNum(10L)
//                .emerDate(LocalDateTime.now())
//                .who("John Doe")
//                .emcheck(1L)
//                .balju(balju)
//                .build();
//        emergencyRepository.save(emergency);
//
//        // Act
//        List<EmergencyDTO> result = emergencyimpl.findByBalju(1L);
//
//        // No assertions, just check the size of the result
//        System.out.println("EmergencyDTOs for Balju ID 1: " + result);
  }
