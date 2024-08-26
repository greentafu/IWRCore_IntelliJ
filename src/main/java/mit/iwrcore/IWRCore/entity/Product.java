package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"member", "proS"})
@Setter
@Table
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manuCode;
    @NotNull
    private String name;
    private String color;
    private String text;
    private String uuid;
    private String supervisor;
    @NotNull
    private Long mater_imsi;
    @NotNull
    private Long mater_check;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mno")
    @NotNull
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="proScode")
    @NotNull
    private ProS proS;

    @OneToMany(mappedBy = "product")
    private List<ProPlan> proPlans; // 연관된 ProPlan 목록

    @OneToMany(mappedBy = "product")
    private List<Plan> plans; // 연관된 Plan 목록

}
