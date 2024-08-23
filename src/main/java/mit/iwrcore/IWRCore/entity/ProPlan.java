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
@Setter
@ToString(exclude = {"writer", "product"})
public class ProPlan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proplanNo;       // 생산계획 번호
    @NotNull
    @Builder.Default
    private Long pronum = 0L;         // 수량
    private String filename;     // 파일
    @NotNull
    private LocalDateTime startDate;  // 시작일
    @NotNull
    private LocalDateTime endDate;    // 마감일

    @NotNull
    private String line;         // 라인

    private String details;      // 상세내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")  // 외래 키 컬럼 이름
    @NotNull
    private Member writer;           // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;      // Product 엔티티와의 관계


}
