package mit.iwrcore.IWRCore.security.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProMDTO {
    private Long manuMcode;
    private String Mname;

    public ProMDTO() {

    }
    public ProMDTO(Long manuMcode, String Mname) {
        super();
        this.manuMcode = manuMcode;
        this.Mname = Mname;
    }

    public Long getcode() {
        return manuMcode;
    }

    public void setcode(Long manuMcode) {
        this.manuMcode = manuMcode;
    }

    public String getName() {
        return Mname;
    }

    public void setName(String Mname) {
        this.Mname = Mname;
    }
}
