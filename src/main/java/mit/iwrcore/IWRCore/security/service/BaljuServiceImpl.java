package mit.iwrcore.IWRCore.security.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Balju;
import mit.iwrcore.IWRCore.entity.Contract;
import mit.iwrcore.IWRCore.entity.JodalChasu;
import mit.iwrcore.IWRCore.entity.JodalPlan;
import mit.iwrcore.IWRCore.repository.BaljuRepository;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractBaljuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractJodalChasyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor


public class BaljuServiceImpl implements BaljuService {
    private final BaljuRepository baljuRepository; // Balju 엔티티를 위한 리포지토리
    private final MemberService memberService; // Member 엔티티를 위한 리포지토리
    private final ContractService contractService; // Contract 엔티티를 위한 리포지토리


    // DTO를 엔티티로 변환
    @Override
    public Balju convertToEntity(BaljuDTO dto) {
        return Balju.builder()
                .baljuNo(dto.getBaljuNo())
                .baljuNum(dto.getBaljuNum())
                .baljuDate(dto.getBaljuDate())
                .baljuWhere(dto.getBaljuWhere())
                .baljuPlz(dto.getBaljuPlz())
                .filename(dto.getFilename())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO())) // DTO를 엔티티로 변환
                .contract(contractService.convertToEntity(dto.getContractDTO())) // DTO를 엔티티로 변환
                .build();
    }

    // 엔티티를 DTO로 변환
    @Override
    public BaljuDTO convertToDTO(Balju entity) {
        return BaljuDTO.builder()
                .baljuNo(entity.getBaljuNo())
                .baljuNum(entity.getBaljuNum())
                .baljuDate(entity.getBaljuDate())
                .baljuWhere(entity.getBaljuWhere())
                .baljuPlz(entity.getBaljuPlz())
                .filename(entity.getFilename())
                .memberDTO(memberService.memberTodto(entity.getWriter())) // 엔티티를 DTO로 변환
                .contractDTO(contractService.convertToDTO(entity.getContract())) // 엔티티를 DTO로 변환
                .build();
    }

    @Override
    public BaljuDTO createBalju(BaljuDTO baljuDTO) {
        Balju balju = convertToEntity(baljuDTO);
        Balju savedBalju = baljuRepository.save(balju);
        return convertToDTO(savedBalju);
    }

    @Override
    public BaljuDTO getBaljuById(Long id) {
        return convertToDTO(baljuRepository.findById(id).get());
    }

    @Override
    public BaljuDTO updateBalju(Long id, BaljuDTO baljuDTO) {
        if (!baljuRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 BaljuDTO를 찾을 수 없습니다.");
        }
        Balju balju = convertToEntity(baljuDTO);
        balju.setBaljuNo(id); // 수정할 때 ID를 설정합니다.
        Balju updatedBalju = baljuRepository.save(balju);
        return convertToDTO(updatedBalju);
    }

    @Override
    public void deleteBalju(Long id) {
        if (!baljuRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 BaljuDTO를 찾을 수 없습니다.");
        }
        baljuRepository.deleteById(id);
    }

    @Override
    public List<BaljuDTO> getAllBaljus() {
        return baljuRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PageResultDTO<ContractBaljuDTO, Object[]> finishedContract(PageRequestDTO2 requestDTO){
        Pageable pageable=requestDTO.getPageable(Sort.by("conNo").descending());
        Page<Object[]> entityPage=baljuRepository.finishContract(pageable);
        return new PageResultDTO<>(entityPage, this::ContractBaljuToDTO);
    }
    private ContractBaljuDTO ContractBaljuToDTO(Object[] objects){
        Contract contract=(Contract) objects[0];
        Balju balju=(Balju) objects[1];
        ContractDTO contractDTO=contractService.convertToDTO(contract);
        BaljuDTO baljuDTO=(balju!=null)? convertToDTO(balju):null;
        return new ContractBaljuDTO(contractDTO, baljuDTO);
    }
}