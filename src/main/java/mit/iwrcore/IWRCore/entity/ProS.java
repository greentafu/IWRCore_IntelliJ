package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="pros")
//@ToString(exclude = "manuMcode")
public class ProS{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manu_scode")
    private Long proScode;
    @Column(name = "sname")
    @NotNull
    private String Sname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manu_mcode")
    @NotNull
    private ProM proM;
}
