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
    private String name;
    private String color;
    private String text;
    private String uuid;
    private String supervisor;
    private String materImsi;
    private boolean check;

    @Embedded
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="manuScode")
    private ProS proS;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="materCode")
    private Material material;







}