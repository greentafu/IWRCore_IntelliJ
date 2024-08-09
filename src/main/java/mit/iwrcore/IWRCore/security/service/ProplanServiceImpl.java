package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.ProPlan;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.repository.ProplanRepository;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ProplanServiceImpl implements ProplanService{
    private final ProplanRepository proPlanRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ProplanServiceImpl(ProplanRepository proPlanRepository,
                              ProductRepository productRepository,
                              MemberRepository memberRepository) {
        this.proPlanRepository = proPlanRepository;
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void save(ProplanDTO dto) {
        ProPlan proPlan = dtoToEntity(dto, productRepository, memberRepository);
        proPlanRepository.save(proPlan);
    }

    @Override
    public ProplanDTO update(ProplanDTO dto) {
        ProPlan proPlan = dtoToEntity(dto, productRepository, memberRepository);
        ProPlan updatedProPlan = proPlanRepository.save(proPlan);
        return entityToDTO(updatedProPlan);
    }

    @Override
    public void deleteById(Long id) {
        proPlanRepository.deleteById(id);
    }

    @Override
    public ProplanDTO findById(Long id) {
        Optional<ProPlan> proPlan = proPlanRepository.findById(id);
        return proPlan.map(this::entityToDTO).orElse(null);
    }

    @Override
    public List<ProplanDTO> findByPlanId(Long planId) {
        List<ProPlan> proPlans = proPlanRepository.findByProplanNo(planId);
        return proPlans.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProPlan dtoToEntity(ProplanDTO dto, ProductRepository productRepository, MemberRepository memberRepository) {
        return ProPlan.builder()
                .proplanNo(dto.getProplanNo())
                .pronum(dto.getPronum())
                .filename(dto.getFilename())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .line(dto.getLine())
                .details(dto.getDetails())
                .product(productRepository.findById(dto.getProductId()).orElse(null))
                .build();
    }

    @Override
    public ProplanDTO entityToDTO(ProPlan entity) {
        return ProplanDTO.builder()
                .proplanNo(entity.getProplanNo())
                .pronum(entity.getPronum())
                .filename(entity.getFilename())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .line(entity.getLine())
                .details(entity.getDetails())
                .productId(entity.getProduct() != null ? entity.getProduct().getManuCode() : null)
                .build();
    }
}

