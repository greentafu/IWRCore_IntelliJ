package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manuCode;
    private Long abc;
    private String name;
    private String color;
    private String text;
    private String uuid;
    private String supervisor;
    private String materImsi;


    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Scode")
    private ProS proS;
}
