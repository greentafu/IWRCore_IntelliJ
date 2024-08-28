package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.ProPlan;
import mit.iwrcore.IWRCore.entity.Structure;
import mit.iwrcore.IWRCore.repository.ProplanRepository;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ProPlanContractNumDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProplanServiceImpl implements ProplanService{
    private final ProplanRepository proPlanRepository;

    private final ProductService productService;
    private final MemberService memberService;

    private final StructureService structureService;


    @Override
    public ProplanDTO save(ProplanDTO dto) {
        ProPlan proPlan = dtoToEntity(dto);
        ProPlan savedProPlan=proPlanRepository.save(proPlan); // savedProPlan = 저장된 proplan
        return entityToDTO(savedProPlan);
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
    public PageResultDTO<ProplanDTO, ProPlan> proplanList(PageRequestDTO2 requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("proplanNo").descending());
        Page<ProPlan> entityPage=proPlanRepository.findAll(pageable);
        Function<ProPlan, ProplanDTO> fn=(entity->entityToDTO(entity));
        return new PageResultDTO<>(entityPage, fn);
    }
    @Override
    public PageResultDTO<ProPlanContractNumDTO, Object[]> proplanList2(PageRequestDTO2 requestDTO){
        Pageable pageable=requestDTO.getPageable(Sort.by("proplanNo").descending());
        Page<Object[]> entityPage=proPlanRepository.findproPlanList(pageable);
        return new PageResultDTO<>(entityPage, this::exProplan);
    }
    private ProPlanContractNumDTO exProplan(Object[] objects){
        ProPlan proPlan=(ProPlan) objects[0];
        Long jcnum=(Long) objects[1];
        Long contractNum=(Long) objects[2];
        ProplanDTO proplanDTO=(proPlan!=null)?entityToDTO(proPlan):null;
        return new ProPlanContractNumDTO(proplanDTO, (jcnum!=null)?jcnum:0L, (contractNum!=null)?contractNum:0L);
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
                .regDate(entity.getRegDate())
                .productDTO(productService.productEntityToDto(entity.getProduct()))
                .memberDTO(memberService.memberTodto(entity.getWriter()))
                .build();
        return dto;
    }

    @Override
    public Long checkProPlan(Long manuCode){
        return proPlanRepository.checkProPlan(manuCode);
    }
}

