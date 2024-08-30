package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Balju;
import mit.iwrcore.IWRCore.entity.Emergency;;
import mit.iwrcore.IWRCore.entity.ProPlan;
import mit.iwrcore.IWRCore.repository.EmergencyRepository;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.EmergencyDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmergencyServiceImpl implements EmergencyService{
    private final EmergencyRepository emergencyRepository;
    private final BaljuService baljuService;
    private final MemberService memberService;

    @Override
    public Emergency convertToEntity(EmergencyDTO dto) {
        return Emergency.builder()
                .emerNo(dto.getEmerNo())
                .emerNum(dto.getEmerNum())
                .emerDate(dto.getEmerDate())
                .who(dto.getWho())
                .emcheck(dto.getEmcheck())
                .balju(baljuService.convertToEntity(dto.getBaljuDTO()))
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO()))
                .build();
    }

    @Override
    public EmergencyDTO convertToDTO(Emergency entity) {
        Balju balju = entity.getBalju();  // Emergency에서 Balju 엔티티 가져오기
        BaljuDTO baljuDTO = null;

        if (balju != null) {
            baljuDTO = baljuService.convertToDTO(balju);  // Balju 엔티티를 DTO로 변환
        }

        return EmergencyDTO.builder()
                .emerNo(entity.getEmerNo())
                .emerNum(entity.getEmerNum())
                .emerDate(entity.getEmerDate())
                .who(entity.getWho())
                .emcheck(entity.getEmcheck())
                .baljuDTO(baljuDTO)  // DTO에 baljuDTO 설정
                .memberDTO(memberService.memberTodto(entity.getWriter()))
                .build();
    }
    @Override
    public EmergencyDTO createEmergency(EmergencyDTO emergencyDTO) {
        Emergency emergency = convertToEntity(emergencyDTO);
        Emergency savedEmergency = emergencyRepository.save(emergency);
        return convertToDTO(savedEmergency);
    }

    @Override
    public Optional<EmergencyDTO> getEmergencyById(Long id) {
        return emergencyRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public EmergencyDTO updateEmergency(Long id, EmergencyDTO emergencyDTO) {
        if (!emergencyRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 EmergencyDTO를 찾을 수 없습니다.");
        }
        Emergency emergency = convertToEntity(emergencyDTO);
        emergency.setEmerNo(id); // 수정할 때 ID를 설정합니다.
        Emergency updatedEmergency = emergencyRepository.save(emergency);
        return convertToDTO(updatedEmergency);
    }

    @Override
    public void deleteEmergency(Long id) {
        if (!emergencyRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 EmergencyDTO를 찾을 수 없습니다.");
        }
        emergencyRepository.deleteById(id);
    }

    @Override
    public PageResultDTO<EmergencyDTO, Object[]> getAllEmergencies(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("emerNo").descending());
        Page<Object[]> entityPage = emergencyRepository.findEmergency(pageable, requestDTO.getPno());
        return new PageResultDTO<>(entityPage, this::extractEmergencyDTO);
    }

    private EmergencyDTO extractEmergencyDTO(Object[] objects) {
        Emergency emergency = (Emergency) objects[0];
        return (emergency != null) ? convertToDTO(emergency) : null;
    }

    @Override
    public List<EmergencyDTO> getEmergencyByBalju(Long baljuNo) {
        List<Emergency> entityList = emergencyRepository.getEmengencyListByBalju(baljuNo);
        return entityList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}