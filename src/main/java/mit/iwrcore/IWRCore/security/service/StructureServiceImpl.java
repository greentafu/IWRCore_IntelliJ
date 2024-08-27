package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.entity.Structure;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.repository.StructureRepository;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class StructureServiceImpl implements StructureService {
    @Autowired
    private final StructureRepository structureRepository;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final MaterialService materialService;

    @Override
    public void save(StructureDTO dto) {
        Structure structure = structureDtoToEntity(dto); // DTO를 엔티티로 변환
        structureRepository.save(structure);    // 엔티티를 저장
    }

    @Override
    public Structure update(StructureDTO structureDTO) {
        Structure structure=structureDtoToEntity(structureDTO);
        if (structure.getSno() == null || !structureRepository.existsById(structure.getSno())) {
            throw new IllegalArgumentException("Structure with id " + structure.getSno() + " not found.");
        }
        return structureRepository.save(structure);
    }

    @Override
    public void deleteById(Long id) {
        if (structureRepository.existsById(id)) {
            structureRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Structure with id " + id + " not found.");
        }
    }

    @Override
    public List<StructureDTO> findByProduct_ManuCode(Long manuCode) {
        List<Object[]> list=structureRepository.findByProduct_ManuCode(manuCode);
        return list.stream().map(this::exStruct).toList();
    }
    private StructureDTO exStruct(Object[] objects){
        Structure structure=(Structure) objects[0];
        StructureDTO structureDTO=(structure!=null)?structureTodto(structure):null;
        return structureDTO;
    }

    // dto를 entity로
    @Override
    public Structure structureDtoToEntity(StructureDTO dto){
        Structure entity=Structure.builder()
                .sno(dto.getSno())
                .material(materialService.materdtoToEntity(dto.getMaterialDTO()))
                .product(productService.productDtoToEntity(dto.getProductDTO()))
                .quantity(dto.getQuantity())
                .build();
        return entity;
    }
    // entity를 dto로
    @Override
    public StructureDTO structureTodto(Structure entity){
        StructureDTO dto=StructureDTO.builder()
                .sno(entity.getSno())
                .productDTO(productService.productEntityToDto(entity.getProduct()))
                .materialDTO(materialService.materTodto(entity.getMaterial()))
                .quantity(entity.getQuantity())
                .build();
        return dto;
    }

}
