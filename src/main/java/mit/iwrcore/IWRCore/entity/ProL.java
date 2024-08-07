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
    private Long manuLcode;

   @Column(nullable = false)
    private String Lname;
}
