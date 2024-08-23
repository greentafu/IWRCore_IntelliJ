package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="prom")
//@ToString(exclude = "manuLcode")
public class ProM{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manu_mcode")
    private Long proMcode;
    @Column(name = "mname")
    @NotNull
    private String Mname;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name ="manu_lcode")
    @NotNull
    private ProL proL;
}
