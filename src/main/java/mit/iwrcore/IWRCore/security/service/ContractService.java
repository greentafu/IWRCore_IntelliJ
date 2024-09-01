package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Contract;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractJodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.NewOrderDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.StockDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.StockDetailDTO;

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
    PageResultDTO<ContractJodalChasuDTO, Object[]> yesJodalplanMaterial(PageRequestDTO2 requestDTO);
    // 계약서 등록해야 하는 조달계획목록
    PageResultDTO<ContractJodalChasuDTO, Object[]> couldContractMaterial(PageRequestDTO requestDTO);

    // 협력회사용 계약서목록
    PageResultDTO<ContractDTO, Object[]> partnerContractList(PageRequestDTO requestDTO);

    // 발주해야 하는 계약목록(협력회사로 묶음)
    List<NewOrderDTO> newOrderContract(Long pno);

    List<StockDTO> stockList();

    List<StockDetailDTO> detailStock(Long materCode);

}