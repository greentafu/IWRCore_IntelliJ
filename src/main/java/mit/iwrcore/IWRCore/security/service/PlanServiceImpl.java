package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Plan;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.repository.PlanRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.security.dto.PlanDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PlanServiceImpl implements PlanService{
    private final PlanRepository planRepository;
    private final ProductRepository productRepository;

    private final ProductServiceImpl productServiceImpl;

    @Override
    public void save(PlanDTO dto) {
        Plan plan = dtoToEntity(dto);
        planRepository.save(plan);
    }

    @Override
    public void update(PlanDTO planDTO) {
        // Ensure the plan exists before updating
        if (!planRepository.existsById(planDTO.getPlancode())) {
            throw new IllegalArgumentException("Plan not found with id: " + planDTO.getPlancode());
        }
        planRepository.save(dtoToEntity(planDTO));
    }

    @Override
    public void deleteById(Long id) {
        planRepository.deleteById(id);
    }

    @Override
    public List<PlanDTO> findByProductId(Long productId) {
//        List<Plan> plans = planRepository.findByProduct_ManuCode(productId);
//        return plans.stream()
//                .map(this::entityToDTO)
//                .collect(Collectors.toList());

        List<Object[]> list=planRepository.findPlan(productId);
        List<PlanDTO> dtoList=list.stream().map(this::exPlan).toList();
        return dtoList;

    }
    private PlanDTO exPlan(Object[] objects){
        Plan plan=(Plan) objects[0];
        PlanDTO planDTO=(plan!=null)? entityToDTO(plan):null;
        return planDTO;
    }


    @Override
    public Plan dtoToEntity(PlanDTO dto) {
        // Fetch the products associated with the IDs from the DTO
        Plan entity=Plan.builder()
                .plancode(dto.getPlancode())
                .line(dto.getLine())
                .product(productServiceImpl.productDtoToEntity(dto.getProductDTO()))
                .quantity(dto.getQuantity())
                .build();
        return entity;

//        List<Product> products = dto.getProductIds().stream()
//                .map(id -> productRepository.findById(id).orElse(null))
//                .collect(Collectors.toList());
//
//        Product product = products.isEmpty() ? null : products.get(0);
//
//        return Plan.builder()
//                .plancode(dto.getPlancode())
//                .product(product)
//                .quantity(dto.getQuantity())
//                .line(dto.getLine())
//                .build();
    }

    @Override
    public PlanDTO entityToDTO(Plan entity) {
        PlanDTO dto=PlanDTO.builder()
                .plancode(entity.getPlancode())
                .line(entity.getLine())
                .productDTO(productServiceImpl.productEntityToDto(entity.getProduct()))
                .quantity(entity.getQuantity())
                .build();
        return dto;
//        return PlanDTO.builder()
//                .plancode(entity.getPlancode())
//                .quantity(entity.getQuantity())
//                .line(entity.getLine())
//                .productIds(entity.getProduct() != null ?
//                        List.of(entity.getProduct().getManuCode()) : List.of())
//                .build();
    }
}