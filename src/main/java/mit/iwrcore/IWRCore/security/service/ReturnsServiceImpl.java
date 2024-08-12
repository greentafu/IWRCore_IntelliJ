package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;

import mit.iwrcore.IWRCore.entity.Returns;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.repository.ReturnsRepository;
import mit.iwrcore.IWRCore.repository.ShipmentRepository;
import mit.iwrcore.IWRCore.security.dto.ReturnsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReturnsServiceImpl implements ReturnsService{
    private final ReturnsRepository returnsRepository;
    private final ShipmentService shipmentService;
    private final MemberService memberService;

    @Override
    public Returns convertToEntity(ReturnsDTO dto) {
        return Returns.builder()
                .reNO(dto.getReNO())
                .reDetail(dto.getReDetail())
                .whyRe(dto.getWhyRe())
                .bGo(dto.getBGo())
                .filename(dto.getFilename())
                .email(dto.getEmail())
                .shipment(shipmentService.convertToEntity(dto.getShipmentDTO())) // ShipmentDTO를 Shipment로 변환
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO()))       // MemberDTO를 Member로 변환
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
                .shipmentDTO(shipmentService.convertToDTO(entity.getShipment())) // Shipment를 ShipmentDTO로 변환
                .memberDTO(memberService.memberTodto(entity.getWriter()))       // Member를 MemberDTO로 변환
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
}