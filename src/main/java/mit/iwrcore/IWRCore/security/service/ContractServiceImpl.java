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
    @Override
    public void save(ContractDTO dto) {
        Contract contract = dtoToEntity(dto);
        contractRepository.save(contract);
    }

    @Override
    public ContractDTO update(ContractDTO dto) {
        Contract contract = dtoToEntity(dto);
        Contract updatedContract = contractRepository.save(contract);
        return entityToDTO(updatedContract);
    }

    @Override
    public void deleteById(Long id) {
        contractRepository.deleteById(id);
    }

    @Override
    public ContractDTO findById(Long id) {
        Optional<Contract> contract = contractRepository.findById(id);
        return contract.map(this::entityToDTO).orElse(null);
    }

    @Override
    public List<ContractDTO> findAll() {
        return contractRepository.findAll().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    private Contract dtoToEntity(ContractDTO dto) {
        return Contract.builder()
                .conNo(dto.getConNo())
                .conNum(dto.getConNum())
                .money(dto.getMoney())
                .howDate(dto.getHowDate())
                .conDate(dto.getConDate())
                .filename(dto.getFilename())
                .who(dto.getWho())
                .build();
    }

    private ContractDTO entityToDTO(Contract entity) {
        return ContractDTO.builder()
                .conNo(entity.getConNo())
                .conNum(entity.getConNum())
                .money(entity.getMoney())
                .howDate(entity.getHowDate())
                .conDate(entity.getConDate())
                .filename(entity.getFilename())
                .who(entity.getWho())
                .build();
    }
}


