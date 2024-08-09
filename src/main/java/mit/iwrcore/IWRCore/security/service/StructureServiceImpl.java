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

         private final StructureRepository structureRepository;
         private final ProductRepository productRepository;
         private final MaterialRepository materialRepository;

         private final MaterialService materialService;
         private final ProductService productService;


    @Override
    public void save(StructureDTO dto) {
        Structure structure = dtoToEntity(dto); // DTO를 엔티티로 변환
        structureRepository.save(structure);    // 엔티티를 저장
    }

    @Override
    public Structure update(Structure structure) {
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
        return structureRepository.findByProduct_ManuCode(manuCode).stream()
                .map(this::entityToDTO)  // 엔티티를 DTO로 변환
                .collect(Collectors.toList());
    }
}
