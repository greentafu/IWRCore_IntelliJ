package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@Table(name="product")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="manu_code")
    private Long manuCode;
    private String name;
    private String color;
    private String text;
    private String uuid;
    private String supervisor;
    private String mater_imsi;
    private String mater_check;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Structure> structures=new HashSet<>();

    //다대다는 JOinTable 해줘야함.
//    @Setter
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name="product_material",
//            joinColumns = @JoinColumn(name="manu_code"),
//            inverseJoinColumns = @JoinColumn(name="mater_code"))
//    private List<Material> materials;

    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="manu_scode")
    private ProS proS;

}
