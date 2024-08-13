package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"writer", "jodalPlan"})
public class JodalChasu extends BaseEntity {

    private Long joNum;      // 조달 수량
    private LocalDateTime joDate;   // 조달 예정일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")  // 외래 키 컬럼 이름
    private Member writer;            // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jodal_plan_id")  // 외래 키 컬럼 이름
    private JodalPlan jodalPlan;      // 연관된 JodalPlan 엔티티
}
