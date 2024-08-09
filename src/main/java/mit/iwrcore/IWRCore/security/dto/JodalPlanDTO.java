package mit.iwrcore.IWRCore.security.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@Builder
@ToString

public class JodalPlanDTO {
    private Long joNo;       // 조달계획 번호
    private String details; // 세부사항
    private LocalDateTime planDate;  // 계획일자
    private Long writerId;  // 작성자 ID
    private Long contractId; // 계약 ID
    private List<Long> jodalChasuIds; // JodalChasu ID 목록
    private Long proPlanId; // ProPlan ID
}

