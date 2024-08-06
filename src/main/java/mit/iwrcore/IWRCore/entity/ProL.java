package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name="prol")
public class ProL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manu_lcode")
    private Long proLcode;
    @Column(name = "lname")
    private String Lname;
}
