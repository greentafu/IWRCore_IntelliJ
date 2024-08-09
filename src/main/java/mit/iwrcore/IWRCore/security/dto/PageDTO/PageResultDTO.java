package mit.iwrcore.IWRCore.security.dto.PageDTO;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//화면에서 필요한 결과를 출력하는 DTO
//페이지 처리 결과를 Page<Entity>타입으로 반환하는게 목표인 DTO
@Data
public class PageResultDTO<DTO,EN> {

    private List<DTO> dtoList;



    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn) {//function으로 EN>DTO로 바꿔줌.
        //엔티티객체를 DTO로 바꿔서 리스트에 넣어주는 과정.
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}
