package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boxCode;
    @NotNull
    private String boxName;

    @OneToMany(mappedBy = "box", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Material> materials = new ArrayList<>();
}