package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ProS{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manuScode;
    private String Sname;

    @ManyToOne
    @JoinColumn(name="manuMcode")
    private ProM proM;
}
