package mit.iwrcore.IWRCore.security.service;



import mit.iwrcore.IWRCore.entity.Box;
import mit.iwrcore.IWRCore.entity.MaterS;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;


import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MaterialService {

    // 자재 삽입/수정
    void insertj(MaterialDTO dto);
    // 자재 찾기
    MaterialDTO findM (Long matercode);
    // 자재 리스트
    PageResultDTO<MaterialDTO, Material> findMaterialAll(PageRequestDTO requestDTO);//모든 리스트

    List<Material> findMaterialPart(Long boxcode, Long materscode); //일부분(창고별, 자재소분류별)
    List<MaterialDTO> materialList();

    // 자재 삭제
    void deleteJa(Long materCode);

    Material materdtoToEntity(MaterialDTO dto);
    MaterialDTO materTodto(Material entity);

}
