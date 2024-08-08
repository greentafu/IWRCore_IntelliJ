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

import java.util.ArrayList;
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

    @Autowired
    private ProCodeService proCodeService;
    @Autowired
    private MemberService memberService;

    @Override
    public ProductDTO getProductById(Long productID) {
        return productEntityToDto(productRepository.findProduct(productID));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> list=new ArrayList<>();
        productRepository.findAllProduct().stream().forEach(x->list.add(productEntityToDto(x)));
        return list;
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = productDtoToEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        if (productRepository.existsById(productDTO.getManuCode())) {  // ID로 존재 확인
            Product product = productDtoToEntity(productDTO);  // DTO를 엔티티로 변환
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found");  // 예외 처리
        }
    }

    @Override
    public void deleteProduct(Long productID) {
        if (productRepository.existsById(productID)) {  // ID로 존재 확인
            productRepository.deleteById(productID);
        } else {
            throw new RuntimeException("Product not found");  // 예외 처리
        }
    }

    // DTO를 엔티티로 변환
    public Product productDtoToEntity(ProductDTO dto) {
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
                .proS(proCodeService.proSdtoToEntity(dto.getProSDTO()))
                .member(memberService.memberdtoToEntity(dto.getMemberDTO()))
                .build();
    }

    // 엔티티를 DTO로 변환
    public ProductDTO productEntityToDto(Product entity) {
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
                .proSDTO(proCodeService.proSTodto(entity.getProS()))
                .memberDTO(memberService.memberTodto(entity.getMember()))
                .build();
    }

}
