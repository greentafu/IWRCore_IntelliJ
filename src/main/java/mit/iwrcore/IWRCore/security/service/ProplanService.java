package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.ProPlan;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.repository.ProductRepository;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;

import java.util.List;


public interface ProplanService {



    void save(ProplanDTO dto);
    ProplanDTO update(ProplanDTO dto);
    void deleteById(Long id);
    ProplanDTO findById(Long id);
    List<ProplanDTO> findByPlanId(Long planId);

    ProPlan dtoToEntity(ProplanDTO dto, ProductRepository productRepository, MemberRepository memberRepository);
    ProplanDTO entityToDTO(ProPlan entity);


}