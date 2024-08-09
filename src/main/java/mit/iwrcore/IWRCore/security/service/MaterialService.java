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

    // 자재 삽입/수정
    void insertj(MaterialDTO dto);
    // 자재 찾기
    MaterialDTO findM (Long matercode);
    // 자재 리스트
    List<Material> findMaterialAll(); //모든 리스트
    List<Material> findMaterialPart(Long boxcode, Long materscode); //일부분(창고별, 자재소분류별)
    // 자재 삭제
    void deleteJa(Long materCode);


//    //소분류 삽입
//    void insertsmater(MaterSDTO materSDTO);

}
