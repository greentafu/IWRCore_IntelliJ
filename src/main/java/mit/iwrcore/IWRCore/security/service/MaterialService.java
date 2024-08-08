package mit.iwrcore.IWRCore.security.service;



import mit.iwrcore.IWRCore.entity.Box;
import mit.iwrcore.IWRCore.entity.MaterS;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;


import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;

import java.util.List;

public interface MaterialService {

    //자재 속성 삽입/수정
    void insertj(MaterialDTO dto);

    //창고 삽입
    void insertbox(BoxDTO dto);
    //소분류 삽입
    void insertsmater(MaterSDTO materSDTO);



    //자재 삭제
    void deleteJa(Long materCode);
    //창고별 리스트가져오기
    List<MaterialDTO> BfindList(BoxDTO boxDTO);
    //소분류별 자재
    List<MaterialDTO> MfindList(MaterSDTO materSDTO);

    //찾기
    MaterialDTO findM (Long matercode);


    // dto를 entity로
    default Box boxEntity(BoxDTO dto){
        return Box.builder().boxCode(dto.getBoxcode()).build();
    }
    default MaterS matersEntity(MaterSDTO dto){
        return MaterS.builder().materScode(dto.getMaterScode()).Sname(dto.getSname()).build();
    }
    default Material materEntity(MaterialDTO dto){
        return Material.builder().materCode(dto.getMaterCode()).name(dto.getName()).unit(dto.getUnit()).standard(dto.getStandard()).color(dto.getColor()).file(dto.getFile()).box(boxEntity(dto.getBoxDTO())).materCode(matersEntity(dto.getMaterSDTO()).getMaterScode()).build();
    }
    // entity를 dto로
    default MaterialDTO materTodto(Material entity){
        return MaterialDTO.builder().name(entity.getName()).unit(entity.getUnit()).Standard(entity.getStandard()).color(entity.getColor()).file(entity.getFile()).date(entity.getRegDate()).materSDTO(matersTodto(entity.getMaterS())).boxDTO(boxTodto(entity.getBox())).materCode(entity.getMaterCode()).build();
    }
    default BoxDTO boxTodto(Box entity){
        return BoxDTO.builder().boxcode(entity.getBoxCode()).boxname(entity.getBoxName()).build();
    }
    default MaterSDTO matersTodto(MaterS entity){
        return MaterSDTO.builder().materScode(entity.getMaterScode()).Sname(entity.getSname()).build();
    }
}
