package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

public class ProM{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manuMcode;
    private String Mname;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "manuLcode")
    private ProL proL;
}
