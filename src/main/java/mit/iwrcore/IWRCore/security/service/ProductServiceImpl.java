package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.repository.Pro.ProSCodeRepository;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
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
    public Long newProductCount(){
        Long count=productRepository.newProductCount();
        return (count!=null)?count:0L;
    }

    @Override
    public ProductDTO getProductById(Long productID) {
        return productEntityToDto(productRepository.findProduct(productID));
    }

    @Override
    public PageResultDTO<ProductDTO, Product> getAllProducts(PageRequestDTO requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("manuCode").descending());
        Page<Product> entityPage=productRepository.findAll(pageable);
        Function<Product, ProductDTO> fn=(entity->productEntityToDto(entity));
        return new PageResultDTO<>(entityPage, fn);
    }

    @Override
    public PageResultDTO<ProductDTO, Product> getNonPlanProducts(PageRequestDTO requestDTO){
        Pageable pageable=requestDTO.getPageable(Sort.by("manuCode").descending());
        Page<Product> entityPage=productRepository.findNonPlanProduct(pageable);
        Function<Product, ProductDTO> fn=(entity->productEntityToDto(entity));
        return new PageResultDTO<>(entityPage, fn);
    }
    // 임시저장 했으나 최종확인은 하지 않은 제품 리스트
    @Override
    public PageResultDTO<ProductDTO, Product> getNonCheckProducts(PageRequestDTO requestDTO){
        Pageable pageable=requestDTO.getPageable(Sort.by("manuCode").descending());
        Page<Product> entityPage=productRepository.findNonCheckProduct(pageable);
        Function<Product, ProductDTO> fn=(entity->productEntityToDto(entity));
        return new PageResultDTO<>(entityPage, fn);
    }
    // 최종확인한 제품 리스트
    @Override
    public PageResultDTO<ProductDTO, Product> getCheckProducts(PageRequestDTO requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("manuCode").descending());
        Page<Product> entityPage=productRepository.findCheckProduct(pageable);
        Function<Product, ProductDTO> fn=(entity->productEntityToDto(entity));
        return new PageResultDTO<>(entityPage, fn);
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = productDtoToEntity(productDTO);
        Product savedProduct=productRepository.save(product);
        return productEntityToDto(savedProduct);
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
    @Override
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
    @Override
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
                .proPlans(entity.getProPlans().stream()
                        .map(proPlan -> ProplanDTO.builder()
                                .proplanNo(proPlan.getProplanNo())
                                .pronum(proPlan.getPronum())
                                .filename(proPlan.getFilename())
                                .startDate(proPlan.getStartDate())
                                .endDate(proPlan.getEndDate())
                                .line(proPlan.getLine())
                                .details(proPlan.getDetails())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
    @Override
    public List<ProductDTO> searchProducts(String query) {
        List<Product> products;
        if (query == null || query.trim().isEmpty()) {
            products = productRepository.findAll();
        } else {
            products = productRepository.searchProducts(query);
        }
        return products.stream()
                .map(this::productEntityToDto)
                .collect(Collectors.toList());
    }


}


