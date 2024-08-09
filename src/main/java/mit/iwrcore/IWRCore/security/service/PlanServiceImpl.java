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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PlanServiceImpl implements PlanService{
    private final PlanRepository planRepository;
    private final ProductRepository productRepository;

    @Override
    public void save(PlanDTO dto) {
        Plan plan = dtoToEntity(dto);
        planRepository.save(plan);
    }

    @Override
    public Plan update(Plan plan) {
        // Ensure the plan exists before updating
        if (!planRepository.existsById(plan.getPlancode())) {
            throw new IllegalArgumentException("Plan not found with id: " + plan.getPlancode());
        }
        return planRepository.save(plan);
    }

    @Override
    public void deleteById(Long id) {
        planRepository.deleteById(id);
    }

    @Override
    public List<PlanDTO> findByProductId(Long productId) {
        List<Plan> plans = planRepository.findByProducts_ManuCode(productId);
        return plans.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    private Plan dtoToEntity(PlanDTO dto) {
        // Fetch the products associated with the IDs from the DTO
        List<Product> products = dto.getProductIds().stream()
                .map(id -> productRepository.findById(id).orElse(null))
                .collect(Collectors.toList());


        Product product = products.isEmpty() ? null : products.get(0);

        return Plan.builder()
                .plancode(dto.getPlancode())
                .product(product)
                .quantity(dto.getQuantity())
                .line(dto.getLine())
                .build();
    }

    private PlanDTO entityToDTO(Plan entity) {
        return PlanDTO.builder()
                .plancode(entity.getPlancode())
                .quantity(entity.getQuantity())
                .line(entity.getLine())
                .productIds(entity.getProduct() != null ?
                        List.of(entity.getProduct().getManuCode()) : List.of())
                .build();
    }
}