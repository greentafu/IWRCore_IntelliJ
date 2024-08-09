package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Plan extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plancode;  // 기본 키

    @OneToMany(mappedBy = "plan")
    private List<Product> products = new ArrayList<>(); // Plan이 소유하는 Product 목록

    private Long quantity;  // 수량
    private String line;    // 라인


}

