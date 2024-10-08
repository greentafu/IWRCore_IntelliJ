package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.ContractRepository;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractJodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.NewOrderDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.StockDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.StockDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
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
    private final MaterialService materialService;

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
        // JodalPlanDTO 변환
        JodalPlanDTO jodalPlanDTO = jodalPlanService.entityToDTO(entity.getJodalPlan());

        // JodalPlan과 관련된 ProPlanDTO와 MaterialDTO도 가져오기
        ProplanDTO proplanDTO = jodalPlanDTO.getProplanDTO();
        MaterialDTO materialDTO = jodalPlanDTO.getMaterialDTO();

        // ContractDTO로 변환
        return ContractDTO.builder()
                .conNo(entity.getConNo())
                .conNum(entity.getConNum())
                .money(entity.getMoney())
                .howDate(entity.getHowDate())
                .conDate(entity.getConDate())
                .filename(entity.getFilename())
                .who(entity.getWho())
                .regDate(entity.getRegDate())
                .jodalPlanDTO(jodalPlanDTO) // JodalPlanDTO 설정
                .memberDTO(memberService.memberTodto(entity.getWriter())) // MemberDTO 설정
                .partnerDTO(partnerService.partnerTodto(entity.getPartner())) // PartnerDTO 설정
                .build();
    }



    @Override
    public void createContract(ContractDTO contractDTO) {
        Contract contract = convertToEntity(contractDTO);
        Contract savedContract = contractRepository.save(contract);

    }

    @Override
    public ContractDTO getContractById(Long id) {
        return convertToDTO(contractRepository.getReferenceById(id));
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
    public PageResultDTO<ContractJodalChasuDTO, Object[]> yesJodalplanMaterial(PageRequestDTO2 requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("joNo").descending());
        Page<Object[]> entityPage=contractRepository.yesplanMaterial(pageable);
        return new PageResultDTO<>(entityPage, this::JodalPlanContractToDTO);
    }
    @Override
    public PageResultDTO<ContractJodalChasuDTO, Object[]> couldContractMaterial(PageRequestDTO requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("joNo").descending());
        Page<Object[]> entityPage=contractRepository.couldContractMaterial(pageable);
        return new PageResultDTO<>(entityPage, this::JodalPlanContractToDTO);
    }
    private ContractJodalChasuDTO JodalPlanContractToDTO(Object[] objects){
        JodalPlan jodalPlan=(JodalPlan) objects[0];
        Contract contract=(Contract) objects[1];
        JodalChasu jodalChasu=(JodalChasu) objects[2];
        Long allNum=(Long) objects[3];

        ContractDTO contractDTO=(contract!=null)?convertToDTO(contract):null;
        JodalPlanDTO jodalPlanDTO=(jodalPlan!=null)?jodalPlanService.entityToDTO(jodalPlan):null;
        JodalChasuDTO jodalChasuDTO=(jodalChasu!=null)?jodalChasuService.convertToDTO(jodalChasu):null;
        Long num=(allNum!=null)?allNum:0L;

        return new ContractJodalChasuDTO(jodalPlanDTO, contractDTO, jodalChasuDTO, num);
    }

    // 협력회사용 계약서 목록
    @Override
    public PageResultDTO<ContractDTO, Object[]> partnerContractList(PageRequestDTO requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("conNo").descending());
        Page<Object[]> entityPage=contractRepository.partnerContractList(pageable, requestDTO.getPno());
        return new PageResultDTO<>(entityPage, this::exContractDTO);
    }
    private ContractDTO exContractDTO(Object[] objects){
        Contract contract=(Contract) objects[0];
        ContractDTO contractDTO=(contract!=null)? convertToDTO(contract):null;
        return contractDTO;
    }

    @Override
    public List<NewOrderDTO> newOrderContract(Long pno){
        List<Object[]> entityList=contractRepository.newOrderContract(pno);
        List<NewOrderDTO> newOrderDTOList=new ArrayList<>();
        Set<ContractDTO> contractDTOSet=new HashSet<>();
        Set<JodalChasuDTO> jodalChasuDTOSet=new HashSet<>();

        for(Object[] objects:entityList){
            Contract contract=(Contract) objects[0];
            JodalChasu jodalChasu=(JodalChasu) objects[1];
            ContractDTO contractDTO=(contract!=null)?convertToDTO(contract):null;
            JodalChasuDTO jodalChasuDTO=(jodalChasu!=null)?jodalChasuService.convertToDTO(jodalChasu):null;
            contractDTOSet.add(contractDTO);
            jodalChasuDTOSet.add(jodalChasuDTO);

            if(jodalChasuDTOSet.size()==3){
                ContractDTO saveContractDTO=contractDTOSet.stream().toList().get(0);
                List<JodalChasuDTO> saveJodalChasuDTOList=jodalChasuDTOSet.stream().toList();
                List<JodalChasuDTO> sortedJodalChsasuList=saveJodalChasuDTOList.stream()
                        .sorted(Comparator.comparing(JodalChasuDTO::getJcnum))
                        .collect(Collectors.toList());

                NewOrderDTO newOrderDTO=new NewOrderDTO(saveContractDTO, sortedJodalChsasuList, null);
                newOrderDTOList.add(newOrderDTO);
                contractDTOSet.clear();
                jodalChasuDTOSet.clear();
            }
        }
        return newOrderDTOList;
    }

    @Override
    public List<StockDTO> stockList(){
        List<Object[]> entityList=contractRepository.stockList();
        List<StockDTO> dtoList=entityList.stream().map(this::exStockDTO).toList();
        return dtoList;
    }
    private StockDTO exStockDTO(Object[] objects){
        Material material=(Material) objects[0];
        Long tempMoney=(Long) objects[1];
        Long tempSumShip=(Long) objects[2];
        Long tempSumReq=(Long) objects[3];
        Long tempCountBal=(Long) objects[4];

        MaterialDTO materialDTO=(material!=null)?materialService.materTodto(material):null;
        Long money=(tempMoney!=null)?tempMoney:0L;
        Long sumShip=(tempSumShip!=null)?tempSumShip:0L;
        Long sumReq=(tempSumReq!=null)?tempSumReq:0L;
        Long countBal=(tempCountBal!=null)?tempCountBal:0L;

        return new StockDTO(materialDTO, money, sumShip, sumReq, countBal);
    }
    @Override
    public List<StockDetailDTO> detailStock(Long materCode){
        List<Object[]> entityList=contractRepository.detailStock(materCode);
        List<StockDetailDTO> dtoList=entityList.stream().map(this::exStockDetailDTO).toList();
        return dtoList;
    }
    private StockDetailDTO exStockDetailDTO(Object[] objects){
        Contract contract=(Contract) objects[0];
        Long tempSumShip=(Long) objects[1];
        Long tempSumReq=(Long) objects[2];

        ContractDTO contractDTO=(contract!=null)?convertToDTO(contract):null;
        Long sumShip=(tempSumShip!=null)?tempSumShip:0L;
        Long sumReq=(tempSumReq!=null)?tempSumReq:0L;
        return new StockDetailDTO(contractDTO, sumShip, sumReq);
    }

}


