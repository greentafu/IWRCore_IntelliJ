package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"writer", "gumsu"})
public class GumsuChasu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gcnum;

    @Id
    private Long gumsuNum;      // 검수량
    private LocalDateTime gumsuDate; // 검수일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")    // 외래 키 컬럼 이름
    private Member writer;             // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gumsu_id")     // Gumsu 엔티티와의 외래 키 컬럼
    private Gumsu gumsu;               // 연관된 Gumsu 엔티티
}