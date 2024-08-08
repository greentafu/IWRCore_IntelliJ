package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
@Getter
@Setter
@ToString
@Builder
public class PlanDTO {
    private Long proplanNo;       // 생산계획 번호
    private Long pronum;         // 수량
    private String filename;     // 파일
    private LocalDateTime startDate;  // 시작일
    private LocalDateTime endDate;    // 마감일
    private String line;         // 라인
    private String details;      // 상세내용
    private Long writerId;       // 작성자 ID

}
