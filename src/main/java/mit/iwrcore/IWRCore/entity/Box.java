package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String boxName;


}