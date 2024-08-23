package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "partM")
public class PartS{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partScode;
    @NotNull
    private String Sname;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "partMcode")
    @NotNull
    private PartM partM;
}
