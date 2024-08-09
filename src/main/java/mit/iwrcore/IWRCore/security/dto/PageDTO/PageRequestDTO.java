package mit.iwrcore.IWRCore.security.dto.PageDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

//화면에서 전달되는 목록 관련된 데이터에 대한 DTO
//Pageable 객체를 생성하는게 목표인 DTO임.
@Data
@AllArgsConstructor
//객체의 생성과정을 캡슐화하여, 여러 생성자 매개변수를 가진 객체를 일기 쉽고 유지보수하기 좋게 만든다.
@Builder
public class PageRequestDTO {
    private int page;
    private int size;

    public PageRequestDTO() {
        this.page=1;
        this.page=10;
    }
    //interface. set of pages to be printed
    //도메인 패키지
    //getPageable 메소드는 PageRequest(클래스)가 Pageable객체를 생성한걸 리턴함.
    // 즉, Pageable객체를 리턴한거임
    public PageRequest getPageable(Sort sort) {
        return PageRequest.of(page -1, size ,sort);
        //?
    }
}
