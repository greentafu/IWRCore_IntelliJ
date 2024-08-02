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
@ToString(exclude = {"writer", "contract", "jodalChasus"})
public class JodalPlan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long joNo;       // 조달계획 번호
    private String details; // 세부사항
    private LocalDateTime planDate;  // 계획일자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")  // 외래 키 컬럼 이름
    private Member writer;            // 작성자

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")  // 외래 키 컬럼 이름
    private Contract contract;          // 연관된 Contract 엔티티

    @OneToMany(mappedBy = "jodalPlan") // JodalChasu 엔티티와의 1대다 관계
    private List<JodalChasu> jodalChasus; // 이 JodalPlan과 연관된 JodalChasu 목록
}
