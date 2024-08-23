package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"writer", "jodalPlan"})
public class JodalChasu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jcnum;

    @NotNull
    private Long joNum;      // 조달 수량
    @NotNull
    private LocalDateTime joDate;   // 조달 예정일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")  // 외래 키 컬럼 이름
    @NotNull
    private Member writer;            // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jodal_plan_id")  // 외래 키 컬럼 이름
    @NotNull
    private JodalPlan jodalPlan;      // 연관된 JodalPlan 엔티티
}
