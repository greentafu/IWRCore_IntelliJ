package mit.iwrcore.IWRCore.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
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
    private Long requstCode;
    private LocalDate eventDate;
    private String text;
    private boolean reqCheck;
    private String line;



}
