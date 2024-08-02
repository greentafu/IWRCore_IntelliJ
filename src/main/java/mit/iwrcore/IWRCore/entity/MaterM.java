package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MaterM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materMcode;
    private String Mname;

    @ManyToOne
    @JoinColumn(name = "materLcode")
    private MaterL materL;


}
