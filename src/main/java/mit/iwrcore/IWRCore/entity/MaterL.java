package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String Lname;
    @OneToMany(mappedBy = "materL")
    private List<MaterM> materMs;
}
