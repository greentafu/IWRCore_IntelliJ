package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;

import mit.iwrcore.IWRCore.entity.Balju;
import mit.iwrcore.IWRCore.entity.ProPlan;
import mit.iwrcore.IWRCore.entity.Returns;
import mit.iwrcore.IWRCore.entity.Shipment;
import mit.iwrcore.IWRCore.repository.ReturnsRepository;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ReturnBaljuDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReturnsServiceImpl implements ReturnsService {
    private static final Logger log = LoggerFactory.getLogger(ReturnsServiceImpl.class);
    private final ReturnsRepository returnsRepository;
    private final ShipmentService shipmentService;
    private final MemberService memberService;
    private final BaljuService baljuService;

    @Override
    public Returns convertToEntity(ReturnsDTO dto) {
        // 수정: Returns 엔티티를 생성할 때, Shipment 엔티티를 조회하여 설정합니다.
        Shipment shipment = dto.getShipmentDTO() != null
                ? shipmentService.convertToEntity(dto.getShipmentDTO())
                : null;

        return Returns.builder()
                .reNO(dto.getReNO())
                .reDetail(dto.getReDetail())
                .whyRe(dto.getWhyRe())
                .bGo(dto.getBGo())
                .filename(dto.getFilename())
                .email(dto.getEmail())
                .returnCheck(dto.getReturnCheck())
                .shipment(shipment)
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO()))
                .build();
    }

    @Override
    public ReturnsDTO convertToDTO(Returns entity) {
        return ReturnsDTO.builder()
                .reNO(entity.getReNO())
                .reDetail(entity.getReDetail())
                .whyRe(entity.getWhyRe())
                .bGo(entity.getBGo())
                .filename(entity.getFilename())
                .email(entity.getEmail())
                .returnCheck(entity.getReturnCheck())
                .regDate(entity.getRegDate())
                .shipmentDTO(entity.getShipment() != null ? shipmentService.convertToDTO(entity.getShipment()) : null)
                .memberDTO(memberService.memberTodto(entity.getWriter()))
                .build();
    }

    @Override
    public ReturnsDTO createReturns(ReturnsDTO returnsDTO) {
        Returns returns = convertToEntity(returnsDTO);
        Returns savedReturns = returnsRepository.save(returns);
        return convertToDTO(savedReturns);
    }

    @Override
    public Optional<ReturnsDTO> getReturnsById(Long id) {
        return returnsRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ReturnsDTO updateReturns(Long id, ReturnsDTO returnsDTO) {
        if (!returnsRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 ReturnsDTO를 찾을 수 없습니다.");
        }
        Returns returns = convertToEntity(returnsDTO);
        returns.setReNO(id); // 수정할 때 ID를 설정합니다.
        Returns updatedReturns = returnsRepository.save(returns);
        return convertToDTO(updatedReturns);
    }

    @Override
    public void deleteReturns(Long id) {
        if (!returnsRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 ReturnsDTO를 찾을 수 없습니다.");
        }
        returnsRepository.deleteById(id);
    }

    @Override
    public List<ReturnsDTO> getAllReturns() {
        return returnsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addReturns(ReturnsDTO returnsDTO, MemberDTO memberDTO, Long shipNo){
        returnsDTO.setReturnCheck(0L);
        returnsDTO.setMemberDTO(memberDTO);
        ReturnsDTO savedReturnDTO=createReturns(returnsDTO);

        Shipment shipment=shipmentService.findShipmentEntity(shipNo);

        returnsRepository.updateReturnsShipment(shipment, savedReturnDTO.getReNO());
    }

    @Override
    public void addReturnCheck(Long reNO){
        returnsRepository.updateReturnsCheck(reNO);
    }

    @Override
    public List<ReturnsDTO> getReturnsList(Long baljuNo){
        List<Returns> entityList=returnsRepository.getReturns(baljuNo);
        List<ReturnsDTO> dtoList=entityList.stream().map(this::convertToDTO).toList();
        return dtoList;
    }

    @Override
    public PageResultDTO<ReturnBaljuDTO, Object[]> getReturnPage(PageRequestDTO requestDTO, Long pno) {
        Pageable pageable=requestDTO.getPageable(Sort.by("reNO").descending());
        Page<Object[]> entityPage=returnsRepository.pageReturns(pageable, pno);
        Function<Object[], ReturnBaljuDTO> fn=(entity->returnBaljuToDTO(entity));
        return new PageResultDTO<>(entityPage, fn);
    }
    private ReturnBaljuDTO returnBaljuToDTO(Object[] objects) {
        Returns returns=(Returns) objects[0];
        Long shipNum=(Long) objects[1];
        LocalDateTime regDate=(LocalDateTime) objects[2];
        Balju balju=(Balju) objects[3];
        ReturnsDTO returnsDTO=convertToDTO(returns);
        BaljuDTO baljuDTO=baljuService.convertToDTO(balju);
        return new ReturnBaljuDTO(returnsDTO, shipNum, regDate, baljuDTO);
    }

    @Override
    public ReturnBaljuDTO getDetailReturn(Long reNo){
        List<Object[]> objects=returnsRepository.detailReturns(reNo);
        return returnBaljuToDTO(objects.get(0));
    }
}