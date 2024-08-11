package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    //제품목록을 불러올수 있으면댐
    //DB에서 제품번호, 제품명, 대분류, 중분류, 소분류 가져옴.

    //특정 제품 ID로 제품 정보를 가져오는 메서드.
    ProductDTO getProductById(Long productID);

    //제품 목록을 불러오는 메서드.
    PageResultDTO<ProductDTO, Product> getAllProducts(PageRequestDTO requestDTO);
    //제품을 추가하는 메서드.
    void addProduct(ProductDTO productDTO);
    //제품을 업데이트하는 메서드.
    void updateProduct(ProductDTO productDTO);
    //제품을 삭제하는 메서드.
    void deleteProduct(Long productId);

    Product productDtoToEntity(ProductDTO dto);
    ProductDTO productEntityToDto(Product entity);

}
