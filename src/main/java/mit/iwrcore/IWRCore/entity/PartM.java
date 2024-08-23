package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "partL")
public class PartM{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partMcode;
    @NotNull
    private String Mname;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "partLcode")
    @NotNull
    private PartL partL;
}
