package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mater_code") // 외래 키 컬럼 이름
    private Material material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manu_code") // 외래 키 컬럼 이름
    private Product product;

    private long quantity;
}
