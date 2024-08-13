package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Contract;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;

import java.util.List;
import java.util.Optional;

public interface ContractService {
    // DTO를 엔티티로 변환
    Contract convertToEntity(ContractDTO dto);

    // 엔티티를 DTO로 변환
    ContractDTO convertToDTO(Contract entity);

    // 계약 생성
    void createContract(ContractDTO contractDTO);

    // 계약 ID로 조회
    Optional<ContractDTO> getContractById(Long id);

    // 계약 업데이트
    ContractDTO updateContract(Long id, ContractDTO contractDTO);

    // 계약 삭제
    void deleteContract(Long id);

    // 모든 계약 조회
    List<ContractDTO> getAllContracts();
}