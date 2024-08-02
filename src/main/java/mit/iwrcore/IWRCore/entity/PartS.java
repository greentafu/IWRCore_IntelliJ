package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

public class PartS{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partScode;
    private String Sname;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "partMcode")
    private PartM partM;
}
