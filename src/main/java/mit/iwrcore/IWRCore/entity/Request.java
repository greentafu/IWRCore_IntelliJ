package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Request extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requstCode;
    @NotNull
    private Long requestNum;
    @NotNull
    private LocalDateTime eventDate;
    private String text;
    @NotNull
    private Long reqCheck;
    @NotNull
    private String line;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proplan_no")
    @NotNull
    private ProPlan proPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materCode")
    @NotNull
    private Material material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")    // 외래 키 컬럼 이름
    @NotNull
    private Member writer;              // 작성자
}
