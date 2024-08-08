package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Plan extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plancode;  // 기본 키

    @OneToMany(mappedBy = "plan")
    private List<Product> products;  // 연관된 Product 목록

    private Long quantity;  // 수량
    private String line;    // 라인


}

