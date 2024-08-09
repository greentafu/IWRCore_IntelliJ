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
@ToString(exclude = {"writer", "jodalPlan"})
public class Contract extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conNo;       // 계약서 번호
    private Long conNum;      // 수량
    private Long money;       // 단가
    private Long howDate;     // 걸리는 일수
    private LocalDateTime conDate;  // 체결일
    private String filename; // 파일
    private String who;      // 담당자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")  // 외래 키 컬럼 이름
    private Member writer;            // 작성자

    @OneToOne(mappedBy = "contract")  // JodalPlan과의 1대1 관계
    private JodalPlan jodalPlan;        // 연관된 JodalPlan 엔티티

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "partner_id")  // 외래 키 컬럼 이름
//    private Partner partner;           // 연관된 Partner 엔티티
//    @OneToMany(mappedBy = "contract")  // Order 엔티티와의 1대다 관계
//    private List<Balju> baljus;        // 이 Contract와 연관된 Order 목록
}
