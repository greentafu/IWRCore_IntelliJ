package mit.iwrcore.IWRCore.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
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

    @Embedded
    private Member member;

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
