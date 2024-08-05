package mit.iwrcore.IWRCore.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProSDTO {
    private Long manuScode;
    private String Sname;

    public ProSDTO() {

    }
    public ProSDTO(Long manuScode, String Sname) {
        super();
        this.manuScode = manuScode;
        this.Sname = Sname;
    }

    public Long getcode() {
        return manuScode;
    }

    public void setcode(Long manuScode) {
        this.manuScode = manuScode;
    }

    public String getName() {
        return Sname;
    }

    public void setName(String Sname) {
        this.Sname = Sname;
    }

}
