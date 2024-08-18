package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Request extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requstCode;
    private Long requestNum;
    private LocalDateTime eventDate;
    private String text;
    private Long reqCheck;
    private String line;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proplan_no")
    private ProPlan proPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materCode")
    private Material material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")    // 외래 키 컬럼 이름
    private Member writer;              // 작성자
}
