package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String Sname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materMcode")
    private MaterM materM;


}
