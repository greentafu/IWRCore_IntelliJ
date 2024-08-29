package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.BaljuRepository;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractBaljuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.NewOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor


public class BaljuServiceImpl implements BaljuService {

    private final BaljuRepository baljuRepository; // Balju 엔티티를 위한 리포지토리
    private final MemberService memberService; // Member 엔티티를 위한 리포지토리
    private final ContractService contractService; // Contract 엔티티를 위한 리포지토리
    private final JodalChasuService jodalChasuService;


    // DTO를 엔티티로 변환
    @Override
    public Balju convertToEntity(BaljuDTO dto) {
        return Balju.builder()
                .baljuNo(dto.getBaljuNo())
                .baljuNum(dto.getBaljuNum())
                .baljuWhere(dto.getBaljuWhere())
                .baljuPlz(dto.getBaljuPlz())
                .filename(dto.getFilename())
                .finCheck(dto.getFinCheck())
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
                .baljuWhere(entity.getBaljuWhere())
                .baljuPlz(entity.getBaljuPlz())
                .filename(entity.getFilename())
                .finCheck(entity.getFinCheck())
                .regDate(entity.getRegDate())
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
        List<Object[]> list=baljuRepository.findBaljuFromNo(id);
        Balju balju=(Balju) list.get(0)[0];
        return convertToDTO(balju);
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
    public PageResultDTO<BaljuDTO, Balju> finishedBalju(PageRequestDTO2 requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("baljuNo").descending());
        Page<Balju> entityPage=baljuRepository.finishBalju(pageable);
        Function<Balju, BaljuDTO> fn=(entity->convertToDTO(entity));
        return new PageResultDTO<>(entityPage, fn);
    }
    @Override
    public PageResultDTO<ContractBaljuDTO, Object[]> finBaljuPage(PageRequestDTO2 requestDTO){
        Pageable pageable=requestDTO.getPageable(Sort.by("baljuNo").descending());
        Page<Object[]> entityPage=baljuRepository.finBaljuPage(pageable);
        return new PageResultDTO<>(entityPage, this::ContractBaljuToDTO);
    }

    @Override
    public PageResultDTO<ContractBaljuDTO, Object[]> finishedContract(PageRequestDTO2 requestDTO){
        Pageable pageable=requestDTO.getPageable(Sort.by("conNo").descending());
        Page<Object[]> entityPage=baljuRepository.finishContract(pageable);
        return new PageResultDTO<>(entityPage, this::ContractBaljuToDTO);
    }
    @Override
    public PageResultDTO<ContractBaljuDTO, Object[]> couldBalju(PageRequestDTO requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("conNo").descending());
        Page<Object[]> entityPage=baljuRepository.couldBalju(pageable);
        return new PageResultDTO<>(entityPage, this::ContractBaljuToDTO);
    }
    private ContractBaljuDTO ContractBaljuToDTO(Object[] objects){
        Contract contract=(Contract) objects[0];
        Balju balju=(Balju) objects[1];
        ContractDTO contractDTO=contractService.convertToDTO(contract);
        BaljuDTO baljuDTO=(balju!=null)? convertToDTO(balju):null;
        return new ContractBaljuDTO(contractDTO, baljuDTO);
    }

    // 협력회사용 발주서
    @Override
    public PageResultDTO<BaljuDTO, Object[]> partnerBaljuList(PageRequestDTO requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("baljuNo").descending());
        Page<Object[]> entityPage=baljuRepository.partnerBaljuList(pageable, requestDTO.getPno());
        return new PageResultDTO<>(entityPage, this::extractBaljuDTO);
    }

    @Override
    public List<BaljuDTO> partListBalju(Long pno){
        List<Object[]> entityList=baljuRepository.partListBalju(pno);
        List<BaljuDTO> dtoList=entityList.stream().map(this::extractBaljuDTO).toList();
        return dtoList;
    }
    private BaljuDTO extractBaljuDTO(Object[] objects){
        Balju balju=(Balju) objects[0];
        BaljuDTO baljuDTO=(balju!=null)?convertToDTO(balju):null;
        return baljuDTO;
    }

    @Override
    public List<NewOrderDTO> modifyBalju(Long pno){
        List<Object[]> entityList=baljuRepository.modifyBalju(pno);
        List<NewOrderDTO> newOrderDTOList=new ArrayList<>();
        Set<ContractDTO> contractDTOSet=new HashSet<>();
        Set<JodalChasuDTO> jodalChasuDTOSet=new HashSet<>();
        Set<BaljuDTO> baljuDTOSet=new HashSet<>();

        for(Object[] objects:entityList){
            Contract contract=(Contract) objects[0];
            JodalChasu jodalChasu=(JodalChasu) objects[1];
            Balju balju=(Balju) objects[2];
            ContractDTO contractDTO=(contract!=null)?contractService.convertToDTO(contract):null;
            JodalChasuDTO jodalChasuDTO=(jodalChasu!=null)?jodalChasuService.convertToDTO(jodalChasu):null;
            BaljuDTO baljuDTO=(balju!=null)?convertToDTO(balju):null;
            contractDTOSet.add(contractDTO);
            jodalChasuDTOSet.add(jodalChasuDTO);
            baljuDTOSet.add(baljuDTO);

            if(jodalChasuDTOSet.size()==3){
                ContractDTO saveContractDTO=contractDTOSet.stream().toList().get(0);
                List<JodalChasuDTO> saveJodalChasuDTOList=jodalChasuDTOSet.stream().toList();
                List<JodalChasuDTO> sortedJodalChsasuList=saveJodalChasuDTOList.stream()
                        .sorted(Comparator.comparing(JodalChasuDTO::getJcnum))
                        .collect(Collectors.toList());
                BaljuDTO saveBaljuDTO=baljuDTOSet.stream().toList().get(0);

                NewOrderDTO newOrderDTO=new NewOrderDTO(saveContractDTO, sortedJodalChsasuList, saveBaljuDTO);
                newOrderDTOList.add(newOrderDTO);
                contractDTOSet.clear();
                jodalChasuDTOSet.clear();
                baljuDTOSet.clear();
            }
        }
        return newOrderDTOList;
    }
}