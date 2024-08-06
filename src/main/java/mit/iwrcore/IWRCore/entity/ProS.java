package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
//@ToString(exclude = "manuMcode")
public class  ProS{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manuScode;
    private String Sname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manuMcode")
    private ProM proM;
}
