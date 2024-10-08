package mit.iwrcore.IWRCore.security.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProplanDTO {
    private Long proplanNo;
    private Long pronum;
    private String filename;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String line;
    private LocalDateTime regDate;
//    private Long productId;
    private String details;
    private ProductDTO productDTO;
    private MemberDTO memberDTO;
//    private Long writerId;  // 작성자 ID
    //private List<Long> jodalPlanIds; // 연관된 JodalPlan ID 목록

}
