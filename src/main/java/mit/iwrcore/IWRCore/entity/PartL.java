package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PartL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partLcode;
    @NotNull
    private String Lname;
}