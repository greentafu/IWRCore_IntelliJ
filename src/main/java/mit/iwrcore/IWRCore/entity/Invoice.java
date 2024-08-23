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
@Setter
@Getter
@ToString(exclude = {"writer"})
public class Invoice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tranNO;                    // 거래명세서 번호

    @NotNull
    private String plz;                    // 영수.정구 (영수증 여부)
    @NotNull
    private LocalDateTime dateCreated;      // 작성일
    private String filename;                // 파일

    private String text;

    private Long cash;
    private Long cheque;
    private Long promissory;
    private Long receivable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")        // 외래 키 컬럼 이름
    @NotNull
    private Member writer;                  // 작성자

}
