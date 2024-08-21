package mit.iwrcore.IWRCore.security.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ContractDTO {
    private Long conNo;
    private Long conNum;
    private Long money;
    private Long howDate;
    private LocalDateTime conDate;
    private String filename;
    private String who;
    private LocalDateTime regDate;

    private JodalPlanDTO jodalPlanDTO;
    private MemberDTO memberDTO;
    private PartnerDTO partnerDTO;
}