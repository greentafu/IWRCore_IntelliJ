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
    private Long manuCode;
    private String name;
    private String color;
    private String text;
    private String uuid;
    private String supervisor;
    private Long mater_imsi;
    private Long mater_check;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mno")
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="proScode")
    private ProS proS;

    @OneToMany(mappedBy = "product")
    private List<ProPlan> proPlans; // 연관된 ProPlan 목록

    @OneToMany(mappedBy = "product")
    private List<Plan> plans; // 연관된 Plan 목록

}
