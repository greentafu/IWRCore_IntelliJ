package mit.iwrcore.IWRCore.security.service;



import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;


import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;

import java.util.List;

public interface MaterialService {
    //자재 삽입/수정
    void insertJa(MaterialDTO dto);
    //자재 삭제
    void deleteJa(Long materCode);
    //창고별 리스트가져오기
    List<MaterialDTO> BfindList(BoxDTO boxDTO);
    //소분류별 자재
    List<MaterialDTO> MfindList(MaterSDTO materSDTO);

    //찾기
    MaterialDTO findM (Long matercode);

    // dto를 entity로
    default Material materEntity(MaterialDTO dto){
        return Material.builder().materCode(dto.getMaterCode()).name(dto.getName()).unit(dto.getUnit()).standard(dto.getStandard()).color(dto.getColor()).file(dto.getFile()).build();
    }
    // entity를 dto로
    default MaterialDTO materTodto(Material entity){
        return MaterialDTO.builder().materCode(entity.getMaterCode()).name(entity.getName()).unit(entity.getUnit()).Standard(entity.getStandard()).color(entity.getColor()).file(entity.getFile()).date(entity.getRegDate()).build();
    }
}
