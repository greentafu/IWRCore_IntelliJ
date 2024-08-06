package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String Mname;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name ="manu_lcode")
    private ProL proL;
}
