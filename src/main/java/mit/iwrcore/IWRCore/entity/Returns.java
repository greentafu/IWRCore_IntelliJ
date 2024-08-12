package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"shipment", "writer"})
public class Returns extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reNO;                   // 반품 번호

    private String reDetail;            // 반품 내용
    private String whyRe;               // 반품 이유
    private String bGo;                 // 비고
    private String filename;            // 파일
    private String email;               // 담당자 이메일

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id")  // 외래 키 컬럼 이름
    private Shipment shipment;          // 관련된 Shipment

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")    // 외래 키 컬럼 이름
    private Member writer;              // 작성자
}
