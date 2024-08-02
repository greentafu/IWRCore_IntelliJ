package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MaterL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materLcode;
    private String Lname;


}
