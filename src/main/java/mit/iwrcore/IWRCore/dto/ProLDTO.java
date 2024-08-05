package mit.iwrcore.IWRCore.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProLDTO {
    private Long manuLcode;
    private String Lname;

    public ProLDTO() {

    }
    public ProLDTO(Long manuLcode, String Lname) {
        super();
        this.manuLcode = manuLcode;
        this.Lname = Lname;
    }

    public Long getcode() {
        return manuLcode;
    }

    public void setcode(Long manuLcode) {
        this.manuLcode = manuLcode;
    }

    public String getName() {
        return Lname;
    }

    public void setName(String Lname) {
        this.Lname = Lname;
    }

}
