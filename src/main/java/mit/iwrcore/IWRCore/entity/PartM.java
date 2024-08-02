package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

public class PartM{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partMcode;
    private String Mname;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "partLcode")
    private PartL partL;


}
