package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.entity.Structure;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;

import java.util.List;
public interface StructureService {

    void save(StructureDTO dto);
    Structure update(StructureDTO structureDTO);
    void deleteById(Long id);
    List<StructureDTO> findByProduct_ManuCode(Long manuCode);

    Structure structureDtoToEntity(StructureDTO dto);
    StructureDTO structureTodto(Structure entity);

}

