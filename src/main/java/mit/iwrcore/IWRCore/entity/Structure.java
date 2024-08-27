package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"material", "product"})
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materCode") // 외래 키 컬럼 이름
    @NotNull
    private Material material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manuCode") // 외래 키 컬럼 이름
    @NotNull
    private Product product;

    @NotNull
    private long quantity;
}
