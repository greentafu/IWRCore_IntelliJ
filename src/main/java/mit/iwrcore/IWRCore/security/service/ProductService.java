package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    //제품목록을 불러올수 있으면댐
    //DB에서 제품번호, 제품명, 대분류, 중분류, 소분류 가져옴.


    //제품 목록을 불러오는 메서드.
    List<ProductDTO> getAllProducts();
    //특정 제품 ID로 제품 정보를 가져오는 메서드.
    ProductDTO getProductById(Long productID);
//
//    //제품을 추가하는 메서드.
//    ProductDTO addProduct(ProductDTO productDTO);
//    //제품을 업데이트하는 메서드.
//    ProductDTO updateProduct(ProductDTO productDTO);
//    //제품을 삭제하는 메서드.
//    void deleteProduct(Long productId);
//

    // DTO를 엔티티로 변환
    default Product productDtoToEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
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

    // 엔티티를 DTO로 변환
    default ProductDTO productEntityToDto(Product entity) {
        if (entity == null) {
            return null;
        }
        return ProductDTO.builder()
                .manuCode(entity.getManuCode())
                .name(entity.getName())
                .color(entity.getColor())
                .text(entity.getText())
                .uuid(entity.getUuid())
                .supervisor(entity.getSupervisor())
                .mater_imsi(entity.getMater_imsi())
                .mater_check(entity.getMater_check())
                .build();
    }
}
