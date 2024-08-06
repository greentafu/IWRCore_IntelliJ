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
@ToString(exclude = {"writer", "balju", "gumsuChasus"})
public class Gumsu extends BaseEntity {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gumsuNo;   // 검수 계획번호
    private Long make;      // 생산량
    private String who;     // 담당자
    private LocalDateTime gumsuDate; // 등록일자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")    // 외래 키 컬럼 이름
    private Member writer;             // 작성자

    @OneToOne
    @JoinColumn(name = "balju_id")     // Order와의 1대1 관계 설정을 위한 외래 키 컬럼 이름
    private Balju balju;               // 연관된 Order 엔티티

    @OneToMany(mappedBy = "gumsu")
    private List<GumsuChasu> gumsuChasus; // GumsuChasu 엔티티와의 1대다 관계
}