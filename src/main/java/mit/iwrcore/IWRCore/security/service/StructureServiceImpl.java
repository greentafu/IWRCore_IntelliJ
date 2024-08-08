package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Structure;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.repository.StructureRepository;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StructureServiceImpl implements StructureService {

//         private final StructureRepository structureRepository;
//         private final ProductRepository productRepository;
//         private final MaterialRepository materialRepository;
//
//         private final MaterialService materialService;
//         private final ProductService productService;
//
//    @Override
//    public void save(StructureDTO dto) {
//        structureRepository.save(structureDtoToEntity(dto));
//    }
//
//    StructureDTO structureTodto(Structure entity){
//        return StructureDTO.builder()
//                .sno(entity.getSno())
//                .materialDTO(materialService.materTodto(entity.getMaterial()))
//                .productDTO(productService.productEntityToDto(entity.getProduct()))
//                .quantity(entity.getQuantity())
//                .build();
//    }
//
//
//    Structure structureDtoToEntity(StructureDTO dto){
//        return Structure.builder()
//                .sno(dto.getSno())
//                .material(materialService.materEntity(dto.getMaterialDTO()))
//                .product(productService.productDtoToEntity(dto.getProductDTO()))
//                .quantity(dto.getQuantity())
//                .build();
//    }
//
//
//    @Override
//    public Structure update(Structure structure) {
//        if (structure.getSno() == null || !structureRepository.existsById(structure.getSno())) {
//            throw new IllegalArgumentException("Structure with id " + structure.getSno() + " not found.");
//        }
//        return structureRepository.save(structure);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        if (structureRepository.existsById(id)) {
//            structureRepository.deleteById(id);
//        } else {
//            throw new IllegalArgumentException("Structure with id " + id + " not found.");
//        }
//    }
//
//    @Override
//    public List<StructureDTO> findByProduct_ManuCode(Long manuCode) {
//        return structureRepository.findByProduct_ManuCode(manuCode).stream()
//                .map(this::entityToDto)  // 인터페이스에서 제공하는 기본 메서드 사용
//                .collect(Collectors.toList());
//    }
}
