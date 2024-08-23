package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "materMs")
public class MaterL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materLcode;
    @NotNull
    private String Lname;
    @OneToMany(mappedBy = "materL")
    @NotNull
    private List<MaterM> materMs;
}
