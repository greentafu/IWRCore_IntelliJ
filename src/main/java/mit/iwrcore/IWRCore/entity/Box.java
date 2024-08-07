package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"material"})
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boxCode;
    private String boxName;

    @OneToMany(mappedBy = "materCode")
    private List<Material> material;
}