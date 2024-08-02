package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"order","writer"})
public class Emergency extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emerNo;       // 긴급납품 번호
    private Long emerNum;       //긴급납품 수량
    private LocalDateTime emerDate; //수령일자
    private String who;         //담당자
    private boolean check;      //확인여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")    // 외래 키 컬럼 이름
    private Member writer;              // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")  // 외래 키 컬럼 이름
    private Order order;  // 연관된 Order 엔티티


}
