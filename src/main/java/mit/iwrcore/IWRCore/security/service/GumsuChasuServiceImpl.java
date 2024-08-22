package mit.iwrcore.IWRCore.security.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.GumsuChasuRepository;
import mit.iwrcore.IWRCore.repository.GumsuReposetory;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractBaljuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.GumsuChasuContractDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.QuantityDateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GumsuChasuServiceImpl implements GumsuChasuService{
    private static final Logger log = LoggerFactory.getLogger(GumsuChasuServiceImpl.class);
    private final GumsuChasuRepository gumsuChasuRepository;
    private final MemberService memberService;
    private final GumsuService gumsuService;
    private final ContractService contractService;

    @Override
    public GumsuChasu convertToEntity(GumsuChasuDTO dto) {
        return GumsuChasu.builder()
                .gcnum(dto.getGcnum())
                .gumsuNum(dto.getGumsuNum())
                .gumsuDate(dto.getGumsuDate())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO())) // MemberDTO를 Member로 변환
                .gumsu(gumsuService.convertToEntity(dto.getGumsuDTO()))   // GumsuDTO를 Gumsu로 변환
                .build();
    }

    @Override
    public GumsuChasuDTO convertToDTO(GumsuChasu entity) {
        return GumsuChasuDTO.builder()
                .gcnum(entity.getGcnum())
                .gumsuNum(entity.getGumsuNum())
                .gumsuDate(entity.getGumsuDate())
                .memberDTO(memberService.memberTodto(entity.getWriter())) // Member를 MemberDTO로 변환
                .gumsuDTO(gumsuService.convertToDTO(entity.getGumsu()))   // Gumsu를 GumsuDTO로 변환
                .build();
    }

    @Override
    public GumsuChasuDTO createGumsuChasu(GumsuChasuDTO gumsuChasuDTO) {
        GumsuChasu gumsuChasu = convertToEntity(gumsuChasuDTO);
        GumsuChasu savedGumsuChasu = gumsuChasuRepository.save(gumsuChasu);
        return convertToDTO(savedGumsuChasu);
    }

    @Override
    public GumsuChasuDTO getGumsuChasuById(Long id) {
        return convertToDTO(gumsuChasuRepository.findById(id).get());
    }

    @Override
    public GumsuChasuDTO updateGumsuChasu(Long id, GumsuChasuDTO gumsuChasuDTO) {
        if (!gumsuChasuRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 GumsuChasuDTO를 찾을 수 없습니다.");
        }
        GumsuChasu gumsuChasu = convertToEntity(gumsuChasuDTO);
        gumsuChasu.setGumsuNum(id); // 수정할 때 ID를 설정합니다.
        GumsuChasu updatedGumsuChasu = gumsuChasuRepository.save(gumsuChasu);
        return convertToDTO(updatedGumsuChasu);
    }

    @Override
    public void deleteGumsuChasu(Long id) {
        if (!gumsuChasuRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 GumsuChasuDTO를 찾을 수 없습니다.");
        }
        gumsuChasuRepository.deleteById(id);
    }

    @Override
    public PageResultDTO<GumsuChasuDTO, GumsuChasu> getAllGumsuChasus(PageRequestDTO requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("gcnum").descending());
        Page<GumsuChasu> entityPage=gumsuChasuRepository.findAll(pageable);
        Function<GumsuChasu, GumsuChasuDTO> fn=(entity->convertToDTO(entity));
        return new PageResultDTO<>(entityPage, fn);
    }

    @Override
    public List<QuantityDateDTO> partnerMainGumsu(Long baljuNo){
        List<GumsuChasu> entityList=gumsuChasuRepository.getGumsuChasuByBaljuNo(baljuNo);
        List<QuantityDateDTO> list=new ArrayList<>();
        for(GumsuChasu gumsuChasu:entityList){
            QuantityDateDTO quantityDateDTO=QuantityDateDTO.builder()
                    .quantity(gumsuChasu.getGumsuNum())
                    .dueDate(gumsuChasu.getGumsuDate())
                    .build();
            list.add(quantityDateDTO);
        }
        if(list.size()>0) list.get(0).setTotalOrder(entityList.get(0).getGumsu().getMake());
        return list;
    }

    @Override
    public PageResultDTO<GumsuChasuContractDTO, Object[]> getAllGumsuChasuContract(PageRequestDTO requestDTO){
        Pageable pageable=requestDTO.getPageable(Sort.by("gcnum").descending());
        Page<Object[]> entityPage=gumsuChasuRepository.getAllGumsuChasuContract(pageable);
        return new PageResultDTO<>(entityPage, this::gumsuChasuContractToDTO);
    }
    private GumsuChasuContractDTO gumsuChasuContractToDTO(Object[] objects){
        GumsuChasu gumsuChasu=(GumsuChasu) objects[0];
        Contract contract=(Contract) objects[1];
        GumsuChasuDTO gumsuChasuDTO=(gumsuChasu!=null)?convertToDTO(gumsuChasu):null;
        ContractDTO contractDTO=(contract!=null)?contractService.convertToDTO(contract):null;
        return new GumsuChasuContractDTO(gumsuChasuDTO, contractDTO);
    }
}