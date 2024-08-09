package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.security.dto.ContractDTO;

import java.util.List;

public interface ContractService {
    void save(ContractDTO dto);
    ContractDTO update(ContractDTO dto);
    void deleteById(Long id);
    ContractDTO findById(Long id);
    List<ContractDTO> findAll();
}
