package mit.iwrcore.IWRCore.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProLDTO {
    private Long manuLcode;
    private String Lname;


    public Long getcode() {
        return 0L;
    }

    public String getName() {
        return "";
    }
}
