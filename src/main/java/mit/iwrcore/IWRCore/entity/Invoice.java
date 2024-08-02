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
@ToString(exclude = {"writer", "shipments"})
public class Invoice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tranNO;                    // 거래명세서 번호

    private boolean plz;                    // 영수.정구 (영수증 여부)
    private LocalDateTime dateCreated;      // 작성일
    private String filename;                // 파일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")        // 외래 키 컬럼 이름
    private Member writer;                  // 작성자

    @OneToMany(mappedBy = "invoice")       // mappedBy를 통해 양방향 관계 설정
    private List<Shipment> shipments;       // 이 Invoice와 연관된 Shipment 목록
}
