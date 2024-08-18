package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.ContractRepository;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractJodalChasyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final MemberService memberService;
    private final JodalPlanService jodalPlanService;
    private final PartnerService partnerService;
    private final ProplanService proplanService;
    private final JodalChasuService jodalChasuService;

    @Override
    public Contract convertToEntity(ContractDTO dto) {
        return Contract.builder()
                .conNo(dto.getConNo())
                .conNum(dto.getConNum())
                .money(dto.getMoney())
                .howDate(dto.getHowDate())
                .conDate(dto.getConDate())
                .filename(dto.getFilename())
                .who(dto.getWho())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO()))
                .jodalPlan(jodalPlanService.dtoToEntity(dto.getJodalPlanDTO()))
                .partner(partnerService.partnerDtoToEntity(dto.getPartnerDTO()))
                .build();
    }

    @Override
    public ContractDTO convertToDTO(Contract entity) {
        return ContractDTO.builder()
                .conNo(entity.getConNo())
                .conNum(entity.getConNum())
                .money(entity.getMoney())
                .howDate(entity.getHowDate())
                .conDate(entity.getConDate())
                .filename(entity.getFilename())
                .who(entity.getWho())
                .jodalPlanDTO(jodalPlanService.entityToDTO(entity.getJodalPlan()))
                .memberDTO(memberService.memberTodto(entity.getWriter()))
                .partnerDTO(partnerService.partnerTodto(entity.getPartner()))
                .build();
    }

    @Override
    public void createContract(ContractDTO contractDTO) {
        Contract contract = convertToEntity(contractDTO);
        Contract savedContract = contractRepository.save(contract);

    }

    @Override
    public ContractDTO getContractById(Long id) {
        return contractRepository.findById(id).map(this::convertToDTO).get();
    }

    @Override
    public ContractDTO updateContract(ContractDTO contractDTO) {
        if (!contractRepository.existsById(contractDTO.getConNo())) {
            throw new RuntimeException("ID가 " + contractDTO.getConNo() + "인 ContractDTO를 찾을 수 없습니다.");
        }
        Contract contract = convertToEntity(contractDTO);
//        contract.setConNo(contractDTO.getConNo()); // 수정할 때 ID를 설정합니다.
        Contract updatedContract = contractRepository.save(contract);
        return convertToDTO(updatedContract);
    }

    @Override
    public void deleteContract(Long id) {
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 ContractDTO를 찾을 수 없습니다.");
        }
        contractRepository.deleteById(id);
    }

    @Override
    public List<ContractDTO> getAllContracts() {
        return contractRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PageResultDTO<ContractJodalChasyDTO, Object[]> yesJodalplanMaterial(PageRequestDTO2 requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("joNo").descending());
        Page<Object[]> entityPage=contractRepository.yesplanMaterial(pageable);
        return new PageResultDTO<>(entityPage, this::JodalPlanContractToDTO);
    }
    @Override
    public PageResultDTO<ContractJodalChasyDTO, Object[]> couldContractMaterial(PageRequestDTO requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("joNo").descending());
        Page<Object[]> entityPage=contractRepository.couldContractMaterial(pageable);
        return new PageResultDTO<>(entityPage, this::JodalPlanContractToDTO);
    }
    private ContractJodalChasyDTO JodalPlanContractToDTO(Object[] objects){
        JodalPlan jodalPlan=(JodalPlan) objects[0];
        Contract contract=(Contract) objects[1];
        JodalChasu jodalChasu=(JodalChasu) objects[2];
        JodalPlanDTO jodalPlanDTO=(jodalPlan!=null)?jodalPlanService.entityToDTO(jodalPlan):null;
        ContractDTO contractDTO=(contract!=null)?convertToDTO(contract):null;
        JodalChasuDTO jodalChasuDTO=(jodalChasu!=null)?jodalChasuService.convertToDTO(jodalChasu):null;
        return new ContractJodalChasyDTO(jodalPlanDTO, contractDTO, jodalChasuDTO);
    }

    // 협력회사용 계약서 목록
    @Override
    public PageResultDTO<ContractDTO, Contract> partnerContractList(PageRequestDTO requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("conNo").descending());
        Page<Contract> entityPage=contractRepository.partnerContractList(pageable, requestDTO.getPno());
        Function<Contract, ContractDTO> fn=(entity->convertToDTO(entity));
        return new PageResultDTO<>(entityPage, fn);
    }

}


