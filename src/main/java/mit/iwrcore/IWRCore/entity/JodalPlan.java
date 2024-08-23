package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter

@ToString(exclude = {"writer", "proPlan", "material"})
public class JodalPlan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long joNo;       // 조달계획 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")  // 외래 키 컬럼 이름
    @NotNull
    private Member writer;            // 작성자

    @OneToMany(mappedBy = "jodalPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JodalChasu> jodalChasus; // 이 JodalPlan과 연관된 JodalChasu 목록

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proplan_id")  // 외래 키 컬럼 이름
    @NotNull
    private ProPlan proPlan;          // 연관된 ProPlan 엔티티

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    @NotNull
    private Material material;
}
