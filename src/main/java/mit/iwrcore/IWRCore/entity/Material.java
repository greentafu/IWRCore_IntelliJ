package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"writer", "materS", "box"})
@Setter
public class Material extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materCode;
    @NotNull
    private String name;
    private String unit;
    private String standard;
    private String color;
    private String file;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")    // 외래 키 컬럼 이름
    @NotNull
    private Member writer;              // 작성자

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="materScode")
    @NotNull
    private MaterS materS;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "box_code") // 외래 키 설정
    private Box box;


    @OneToMany(mappedBy = "material")
    @Builder.Default
    private Set<Structure> structures=new HashSet<>();
}
