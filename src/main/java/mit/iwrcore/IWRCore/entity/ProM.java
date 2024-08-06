package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
//@ToString(exclude = "manuLcode")
public class ProM{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proMcode;
    private String Mname;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "proLcode")
    private ProL proL;
}
