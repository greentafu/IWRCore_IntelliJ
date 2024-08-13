package mit.iwrcore.IWRCore.security.dto;

import jakarta.persistence.*;
import lombok.*;
import mit.iwrcore.IWRCore.entity.Contract;
import mit.iwrcore.IWRCore.entity.JodalChasu;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.ProPlan;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@Builder
@ToString

public class JodalPlanDTO {
    private Long joNo;       // 조달계획 번호
//    private String details; // 세부사항
    private LocalDateTime planDate;  // 계획일자
//    private String writerId;  // 작성자 ID
//    private Long contractId; // 계약 ID
//    private List<Long> jodalChasuIds; // JodalChasu ID 목록
//    private Long proPlanId; // ProPlan ID
    private MemberDTO memberDTO;            // 작성자
    private ProplanDTO proplanDTO;          // 연관된 ProPlan 엔티티
    private MaterialDTO materialDTO;
}

