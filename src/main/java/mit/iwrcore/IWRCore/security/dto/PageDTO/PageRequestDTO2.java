package mit.iwrcore.IWRCore.security.dto.PageDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO2 {
    private int page2;
    private int size2;

    public PageRequestDTO2(){
        this.page2=1;
        this.size2=2;
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(page2-1, size2, sort);
    }

    // 관리자_회원검색
    private Integer department2;
    private Integer role2;
    private String memberSearch2;
    // 협력회사검색
    private Long partL2;
    private Long partM2;
    private Long partS2;
    private String partnerSearch2;
    // 자재검색
    private Long materL2;
    private Long materM2;
    private Long materS2;
    private String materialSearch2;
}
