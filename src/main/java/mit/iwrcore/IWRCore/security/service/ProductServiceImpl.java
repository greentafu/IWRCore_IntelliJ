package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.ProS;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.repository.ProSCodeRepository;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProSCodeRepository proSRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::productEntityToDto)  // 엔티티를 DTO로 변환
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long productID) {
        return productRepository.findById(productID)
                .map(this::productEntityToDto)  // 엔티티를 DTO로 변환
                .orElse(null);  // 또는 적절한 예외 처리
    }
//
//    @Override
//    public ProductDTO addProduct(ProductDTO productDTO) {
//        Product product = productDtoToEntity(productDTO);  // DTO를 엔티티로 변환
//        // 외래 키 설정
//        if (productDTO.getProS() != null) {
//            ProS proS = proSRepository.findById(productDTO.getProS().getProM().getProMcode())
//                    .orElseThrow(() -> new RuntimeException("ProS not found"));
//            product.setProS(proS);
//        }
//
//        if (productDTO.getMaterial() != null) {
//            Material material = materialRepository.findById(productDTO.getMaterial().getMaterCode())
//                    .orElseThrow(() -> new RuntimeException("Material not found"));
//            product.setMaterials(Collections.singletonList(material)); // 단일 Material을 리스트로 설정
//        }
//
//        Product savedProduct = productRepository.save(product);
//        return productEntityToDto(savedProduct);  // 엔티티를 DTO로 변환
//    }
//
//    @Override
//    public ProductDTO updateProduct(ProductDTO productDTO) {
//        if (productRepository.existsById(productDTO.getManuCode())) {  // ID로 존재 확인
//            Product product = productDtoToEntity(productDTO);  // DTO를 엔티티로 변환
//            Product updatedProduct = productRepository.save(product);
//            return productEntityToDto(updatedProduct);  // 엔티티를 DTO로 변환
//        } else {
//            throw new RuntimeException("Product not found");  // 예외 처리
//        }
//    }
//
//    @Override
//    public void deleteProduct(Long productID) {
//        if (productRepository.existsById(productID)) {  // ID로 존재 확인
//            productRepository.deleteById(productID);
//        } else {
//            throw new RuntimeException("Product not found");  // 예외 처리
//        }
//    }
}
