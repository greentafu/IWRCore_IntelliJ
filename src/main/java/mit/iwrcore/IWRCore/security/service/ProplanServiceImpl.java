package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.ProPlan;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.repository.ProplanRepository;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ProplanServiceImpl implements ProplanService{
    private final ProplanRepository proPlanRepository;

    private final ProductService productService;
    private final MemberService memberService;

    @Override
    public void save(ProplanDTO dto) {
        ProPlan proPlan = dtoToEntity(dto);
        proPlanRepository.save(proPlan);
    }

    @Override
    public void update(ProplanDTO dto) {
        ProPlan proPlan = dtoToEntity(dto);
        proPlanRepository.save(proPlan);
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
    public PageResultDTO<ProplanDTO, ProPlan> proplanList(PageRequestDTO requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("proplanNo").descending());
        Page<ProPlan> entityPage=proPlanRepository.findAll(pageable);
        Function<ProPlan, ProplanDTO> fn=(entity->entityToDTO(entity));
        return new PageResultDTO<>(entityPage, fn);
    }

//    @Override
//    public List<ProplanDTO> findByPlanId(Long planId) {
//        List<ProPlan> proPlans = proPlanRepository.findByProplanNo(planId);
//        return proPlans.stream()
//                .map(this::entityToDTO)
//                .collect(Collectors.toList());
//    }

    @Override
    public ProPlan dtoToEntity(ProplanDTO dto) {
        ProPlan entity=ProPlan.builder()
                .proplanNo(dto.getProplanNo())
                .pronum(dto.getPronum())
                .filename(dto.getFilename())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .line(dto.getLine())
                .details(dto.getDetails())
                .product(productService.productDtoToEntity(dto.getProductDTO()))
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO()))
                .build();
        return entity;
    }

    @Override
    public ProplanDTO entityToDTO(ProPlan entity) {
        ProplanDTO dto=ProplanDTO.builder()
                .proplanNo(entity.getProplanNo())
                .pronum(entity.getPronum())
                .filename(entity.getFilename())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .line(entity.getLine())
                .details(entity.getDetails())
                .productDTO(productService.productEntityToDto(entity.getProduct()))
                .memberDTO(memberService.memberTodto(entity.getWriter()))
                .build();
        return dto;
    }
}

