package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.entity.Structure;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;

import java.util.List;
public interface StructureService {



    void save(StructureDTO dto);
    Structure update(Structure structure);
    void deleteById(Long id);
    List<StructureDTO> findByProduct_ManuCode(Long manuCode);

    default Material dtoToMaterialEntity(MaterialDTO dto) {
        return Material.builder()
                .materCode(dto.getMaterCode())
                .name(dto.getName())
                .unit(dto.getUnit())
                .standard(dto.getStandard())
                .color(dto.getColor())
                .file(dto.getFile())
                .build();
    }

    default Product dtoToProductEntity(ProductDTO dto) {
        return Product.builder()
                .manuCode(dto.getManuCode())
                .name(dto.getName())
                .color(dto.getColor())
                .text(dto.getText())
                .uuid(dto.getUuid())
                .supervisor(dto.getSupervisor())
                .mater_imsi(dto.getMater_imsi())
                .mater_check(dto.getMater_check())
                .build();
    }

    default Structure dtoToEntity(StructureDTO dto) {
        return Structure.builder()
                .sno(dto.getSno())
                .material(dtoToMaterialEntity(dto.getMaterialDTO()))
                .product(dtoToProductEntity(dto.getProductDTO()))
                .quantity(dto.getQuantity())
                .build();
    }

    default MaterialDTO entityToMaterialDTO(Material entity) {
        return MaterialDTO.builder()
                .materCode(entity.getMaterCode())  // 엔티티의 materCode를 DTO의 materCode로 설정
                .name(entity.getName())            // 엔티티의 name을 DTO의 name으로 설정
                .unit(entity.getUnit())            // 엔티티의 unit을 DTO의 unit으로 설정
                .Standard(entity.getStandard())    // 엔티티의 standard를 DTO의 standard로 설정
                .color(entity.getColor())          // 엔티티의 color를 DTO의 color로 설정
                .file(entity.getFile())            // 엔티티의 file을 DTO의 file로 설정
                .build();
    }



    default ProductDTO entityToProductDTO(Product entity) {
        return ProductDTO.builder()
                .manuCode(entity.getManuCode())    // 엔티티의 manuCode를 DTO의 manuCode로 설정
                .name(entity.getName())            // 엔티티의 name을 DTO의 name으로 설정
                .color(entity.getColor())          // 엔티티의 color를 DTO의 color로 설정
                .text(entity.getText())            // 엔티티의 text를 DTO의 text로 설정
                .uuid(entity.getUuid())            // 엔티티의 uuid를 DTO의 uuid로 설정
                .supervisor(entity.getSupervisor()) // 엔티티의 supervisor를 DTO의 supervisor로 설정
                .mater_imsi(entity.getMater_imsi()) // 엔티티의 mater_imsi를 DTO의 mater_imsi로 설정
                .mater_check(entity.getMater_check()) // 엔티티의 mater_check를 DTO의 mater_check로 설정
                .build();
    }


    default StructureDTO entityToDTO(Structure entity) {
        return StructureDTO.builder()
                .sno(entity.getSno())                    // 엔티티의 sno를 DTO의 sno로 설정
                .materialDTO(entityToMaterialDTO(entity.getMaterial())) // 엔티티의 Material을 DTO의 MaterialDTO로 변환하여 설정
                .productDTO(entityToProductDTO(entity.getProduct()))   // 엔티티의 Product를 DTO의 ProductDTO로 변환하여 설정
                .quantity(entity.getQuantity())          // 엔티티의 quantity를 DTO의 quantity로 설정
                .build();
    }
}

