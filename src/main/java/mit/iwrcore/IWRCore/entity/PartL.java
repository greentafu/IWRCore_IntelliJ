package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PartL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partLcode;
    private String Lname;


}