package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String Sname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manu_mcode")
    private ProM proM;
}
