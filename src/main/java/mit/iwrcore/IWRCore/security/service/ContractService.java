package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Contract;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractJodalChasyDTO;

import java.util.List;

public interface ContractService {
    // DTO를 엔티티로 변환
    Contract convertToEntity(ContractDTO dto);

    // 엔티티를 DTO로 변환
    ContractDTO convertToDTO(Contract entity);

    // 계약 생성
    void createContract(ContractDTO contractDTO);

    // 계약 ID로 조회
    ContractDTO getContractById(Long id);

    // 계약 업데이트
    ContractDTO updateContract(ContractDTO contractDTO);

    // 계약 삭제
    void deleteContract(Long id);

    // 모든 계약 조회
    List<ContractDTO> getAllContracts();

    // 조달차수 있는(조달계획한) 자재 목록+계약서 등록여부
    PageResultDTO<ContractJodalChasyDTO, Object[]> yesJodalplanMaterial(PageRequestDTO2 requestDTO);
    // 계약서 등록해야 하는 조달계획목록
    PageResultDTO<ContractJodalChasyDTO, Object[]> couldContractMaterial(PageRequestDTO requestDTO);

}