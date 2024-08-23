package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "materM")
public class MaterS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materScode;
    @NotNull
    private String Sname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materMcode")
    @NotNull
    private MaterM materM;


}
