package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "materMcode")
public class MaterS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materScode;
    private String Sname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materMcode")
    private MaterL materL;


}
