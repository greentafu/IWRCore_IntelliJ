package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;

import java.util.List;

public interface ProductService {
    //제품목록을 불러올수 있으면댐
    //DB에서 제품번호, 제품명, 대분류, 중분류, 소분류 가져옴.

    //특정 제품 ID로 제품 정보를 가져오는 메서드.
    ProductDTO getProductById(Long productID);

    Long newProductCount();

    //제품 목록을 불러오는 메서드.
    PageResultDTO<ProductDTO, Product> getAllProducts(PageRequestDTO requestDTO);
    // 생산계획이 없는 제품 목록
    PageResultDTO<ProductDTO, Product> getNonPlanProducts(PageRequestDTO requestDTO);
    // 임시저장 했으나 최종확인은 하지 않은 제품 리스트
    PageResultDTO<ProductDTO, Product> getNonCheckProducts(PageRequestDTO requestDTO);
    // 최종확인한 제품 리스트
    PageResultDTO<ProductDTO, Product> getCheckProducts(PageRequestDTO requestDTO);

    //제품을 추가하는 메서드.
    ProductDTO addProduct(ProductDTO productDTO);
    //제품을 업데이트하는 메서드.
    void updateProduct(ProductDTO productDTO);
    //제품을 삭제하는 메서드.
    void deleteProduct(Long productId);

    Product productDtoToEntity(ProductDTO dto);
    ProductDTO productEntityToDto(Product entity);
    List<ProductDTO> searchProducts(String query);
    List<ProplanDTO> convertProPlans(Product entity);

}
