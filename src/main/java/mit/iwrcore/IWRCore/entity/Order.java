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
@ToString(exclude = {"writer", "shipments", "gumsu", "emergencies","contract"})
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNo;       // 발주서 번호
    private Long orderNum;      // 수량
    private LocalDateTime orderDate;    // 요청일
    private String orderWhere; // 배송장소
    private String orderPlz;  // 요청사항
    private String filename; // 파일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")    // 외래 키 컬럼 이름
    private Member writer;              // 작성자

    @OneToMany(mappedBy = "order")    // mappedBy 속성 추가
    private List<Shipment> shipments;   // 이 Order와 연관된 Shipment 목록

    @OneToOne(mappedBy = "order")
    private Gumsu gumsu;  // 이 Order와 연관된 Gumsu

    @OneToMany(mappedBy = "order")
    private List<Emergency> emergencies; // 이 Order와 연관된 Emergency 목록

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")  // 외래 키 컬럼 이름
    private Contract contract;  // 연관된 Contract 엔티티
}
