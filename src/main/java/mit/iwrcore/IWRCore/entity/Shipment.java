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
@ToString(exclude = {"writer", "invoice", "balju", "returns"})
public class Shipment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipNO;                // 출고 번호

    @NotNull
    private Long shipNum;               // 출고 수량
    private LocalDateTime receipt;      // 입고 일
    private String text;
    @NotNull
    private Long receiveCheck;
    private String bGo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = true)    // 외래 키 컬럼 이름
    private Member writer;              // 작성자
    @OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Returns returns; // 관련된 Returns

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "invoice_id", nullable = true)   // 외래 키 컬럼 이름
    private Invoice invoice;            // 관련된 Invoice

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @NotNull
    private Balju balju;                // 관련된 Order
}
