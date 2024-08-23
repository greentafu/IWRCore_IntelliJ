package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "materL")
@Setter
public class MaterM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materMcode;
    @NotNull
    private String Mname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materLcode")
    @NotNull
    private MaterL materL;


}
