package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"writer", "invoice", "returns", "balju"})
public class Shipment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipNO;                // 출고 번호

    private Long shipNum;               // 출고 수량
    private LocalDateTime receipt;      // 입고 일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")    // 외래 키 컬럼 이름
    private Partner writer;              // 작성자

    @OneToOne(fetch = FetchType.LAZY)
    private Returns returns;            // 관련된 Returns

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")   // 외래 키 컬럼 이름
    private Invoice invoice;            // 관련된 Invoice

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Balju balju;                // 관련된 Order
}
