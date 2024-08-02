package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Request extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requstCode;
    private LocalDate eventDate;
    private String text;
    private boolean reqCheck;
    private String line;
}
