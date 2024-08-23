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
@ToString(exclude = {"writer", "gumsu"})
public class GumsuChasu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gcnum;

    @NotNull
    private Long gumsuNum;      // 검수량
    @NotNull
    private LocalDateTime gumsuDate; // 검수일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")    // 외래 키 컬럼 이름
    @NotNull
    private Member writer;             // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gumsu_id")     // Gumsu 엔티티와의 외래 키 컬럼
    @NotNull
    private Gumsu gumsu;               // 연관된 Gumsu 엔티티
}