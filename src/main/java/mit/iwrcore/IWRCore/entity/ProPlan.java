package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"writer", "jodalPlans"})
public class ProPlan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proplanNo;       // 생산계획 번호
    private Long pronum;         // 수량
    private String filename;     // 파일
    private LocalDateTime startDate;  // 시작일
    private LocalDateTime endDate;    // 마감일
    private String line;         // 라인
    private String details;      // 상세내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")  // 외래 키 컬럼 이름
    private Member writer;           // 작성자

    @OneToMany(mappedBy = "proPlan")
    private List<JodalPlan> jodalPlans; // 연관된 JodalPlan 목록
}
