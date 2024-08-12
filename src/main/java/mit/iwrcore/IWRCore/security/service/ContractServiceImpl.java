package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Contract;
import mit.iwrcore.IWRCore.repository.ContractRepository;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final MemberService memberService;
    private final JodalPlanService jodalPlanService;
    private final PartnerService partnerService;

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
    public ContractDTO createContract(ContractDTO contractDTO) {
        Contract contract = convertToEntity(contractDTO);
        Contract savedContract = contractRepository.save(contract);
        return convertToDTO(savedContract);
    }

    @Override
    public Optional<ContractDTO> getContractById(Long id) {
        return contractRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ContractDTO updateContract(Long id, ContractDTO contractDTO) {
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 ContractDTO를 찾을 수 없습니다.");
        }
        Contract contract = convertToEntity(contractDTO);
        contract.setConNo(id); // 수정할 때 ID를 설정합니다.
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
}


