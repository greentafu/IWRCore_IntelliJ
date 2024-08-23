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
@ToString(exclude = {"writer", "shipments", "gumsu", "emergencies","contract"})
public class Balju extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long baljuNo;       // 발주서 번호
    @NotNull
    private String baljuWhere; // 배송장소
    private String baljuPlz;  // 요청사항
    private String filename; // 파일
    @NotNull
    @Builder.Default
    private Long finCheck = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")    // 외래 키 컬럼 이름
    @NotNull
    private Member writer;              // 작성자

    @OneToMany(mappedBy = "balju")    // mappedBy 속성 추가
    private List<Shipment> shipments;   // 이 Order와 연관된 Shipment 목록

    @OneToOne(mappedBy = "balju")
    private Gumsu gumsu;  // 이 Order와 연관된 Gumsu

    @OneToMany
    private List<Emergency> emergencies; // 이 Order와 연관된 Emergency 목록

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")  // 외래 키 컬럼 이름
    @NotNull
    private Contract contract;  // 연관된 Contract 엔티티
}
