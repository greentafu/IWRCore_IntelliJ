package mit.iwrcore.IWRCore.security.service;


import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;

import mit.iwrcore.IWRCore.security.dto.MaterialDTO;

import java.util.List;

public interface MaterialService {
    //자재 삽입/수정
    void insertJa(MaterialDTO dto);
    //자재 삭제
    void deleteJa(Long materCode);
    //창고별 리스트가져오기
    List<MaterialDTO> findList(BoxDTO boxDTO);

    // dto를 entity로
    default Material materEntity(MaterialDTO dto){
        return Material.builder().materCode(dto.getMaterCode()).name(dto.getName()).unit(dto.getUnit()).standard(dto.getStandard()).color(dto.getColor()).file(dto.getFile()).build();
    }

}
