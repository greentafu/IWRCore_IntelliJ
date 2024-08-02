package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Material extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materCode;
    private String name;
    private String unit;
    private String standard;
    private String color;
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")    // 외래 키 컬럼 이름
    private Member writer;              // 작성자

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="materScode")
    private MaterS materS;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="boxCode")
    private Box box;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="manuCode")
    private Product product;

}
