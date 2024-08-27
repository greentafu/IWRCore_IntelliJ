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
@ToString(exclude = {"balju", "writer"})
public class Emergency extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emerNo;       // 긴급납품 번호
    @NotNull
    private Long emerNum;      // 긴급납품 수량
    @NotNull
    private LocalDateTime emerDate; // 수령일자
    @NotNull
    private String who;       // 담당자
    @NotNull
    private Long emcheck;    // 확인여부 (boolean 타입)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")  // 외래 키 컬럼 이름
    @NotNull
    private Member writer;          // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "balju_id")  // 외래 키 컬럼 이름
    @NotNull
    private Balju balju;           // 연관된 Balju 엔티티
}
