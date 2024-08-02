package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ProL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manuLcode;
    private String Lname;


}
