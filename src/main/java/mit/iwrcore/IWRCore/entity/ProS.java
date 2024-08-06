package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "proMcode")
public class ProS{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proScode;
    private String Sname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="proMcode")
    private ProM proM;
}
