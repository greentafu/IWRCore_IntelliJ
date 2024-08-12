package mit.iwrcore.IWRCore.security.dto.PageDTO;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {
    // DTO리스트
    private List<DTO> dtoList;

    // 페이지처리
    private int totalPage; // 전체페이지수

    private int page; // 현재 페이지
    private int size; // 볼 목록의 사이즈

    private int start, end; // 페이지 첫, 마지막 번호
    private boolean prev, next; // 이전, 다음 페이지 유무

    private List<Integer> pageList; // 페이지 번호 목록(하단에 나올 것)

    // DTO리스트
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        dtoList=result.stream().map(fn).collect(Collectors.toList()); // dto리스트 채움

        totalPage=result.getTotalPages(); // 전체페이지수

        makePageList(result.getPageable());
    }
    private void makePageList(Pageable pageable){
        this.page=pageable.getPageNumber()+1;
        this.size=pageable.getPageSize();

        int temped=(int)(Math.ceil(page/10.0))*10;

        start=temped-9;
        end=totalPage>temped?temped:totalPage;
        prev=start>1;
        next=totalPage>temped;

        pageList= IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    // 관리자_회원검색
    private Integer department;
    private Integer role;
    private String memberSearch;
    // 협력회사검색
    private Long partL;
    private Long partM;
    private Long partS;
    private String partnerSearch;
    // 자재검색
    private Long materL;
    private Long materM;
    private Long materS;
    private String materialSearch;

}
