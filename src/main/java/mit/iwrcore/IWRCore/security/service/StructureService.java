package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.entity.Structure;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;

import java.util.List;

public interface StructureService {
    Structure save(Structure structure);
    Structure update(Structure structure);
    void deleteById(Long id);
    List<StructureDTO> findByProductId(Long productId);
    void deleteByProductId(Long productId);

    default Structure dtoToEntity(StructureDTO dto, MaterialRepository materialRepository, ProductRepository productRepository) {
        if (dto == null) {
            return null;
        }

        // DTO에서 ID를 사용하여 엔티티를 조회
        Material material = materialRepository.findById(dto.getMaterialId()).orElse(null);
        Product product = productRepository.findById(dto.getProductId()).orElse(null);

        return Structure.builder()
                .sno(dto.getSno())
                .material(material) // 조회된 엔티티를 설정
                .product(product)   // 조회된 엔티티를 설정
                .quantity(dto.getQuantity())
                .build();
    }
    default StructureDTO entityToDto(Structure entity) {
        if (entity == null) {
            return null;
        }

        return StructureDTO.builder()
                .sno(entity.getSno())
                .materialId(entity.getMaterial() != null ? entity.getMaterial().getMaterCode() : null)
                .productId(entity.getProduct() != null ? entity.getProduct().getManuCode() : null)
                .quantity(entity.getQuantity())
                .build();
    }
}

