package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Balju;
import mit.iwrcore.IWRCore.entity.Emergency;;
import mit.iwrcore.IWRCore.entity.ProPlan;
import mit.iwrcore.IWRCore.entity.Request;
import mit.iwrcore.IWRCore.repository.EmergencyRepository;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
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
    private final RequestService requestService;
    @Override
    public Emergency convertToEntity(EmergencyDTO dto) {
        if (dto == null) {
            return null;
        }

        // Emergency 엔티티를 생성하면서 필요한 필드들을 설정합니다.
        Emergency emergency = Emergency.builder()
                .emerNo(dto.getEmerNo())
                .emerNum(dto.getEmerNum())
                .emerDate(dto.getEmerDate())
                .who(dto.getWho())
                .emcheck(dto.getEmcheck())
                .balju(dto.getBaljuDTO() != null ? baljuService.convertToEntity(dto.getBaljuDTO()) : null)
                .writer(dto.getMemberDTO() != null ? memberService.memberdtoToEntity(dto.getMemberDTO()) : null)
                .build();

        // Request 엔티티와의 관계 설정
        if (dto.getRequestDTO() != null) {
            Request request = requestService.convertToEntity(dto.getRequestDTO());
            emergency.setRequest(request);
        }

        return emergency;
    }

    @Override
    public EmergencyDTO convertToDTO(Emergency entity) {
        if (entity == null) {
            return null;
        }

        BaljuDTO baljuDTO = null;
        if (entity.getBalju() != null) {
            baljuDTO = baljuService.convertToDTO(entity.getBalju());
        }

        MemberDTO memberDTO = null;
        if (entity.getWriter() != null) {
            memberDTO = memberService.memberTodto(entity.getWriter());
        }

        return EmergencyDTO.builder()
                .emerNo(entity.getEmerNo())
                .emerNum(entity.getEmerNum())
                .emerDate(entity.getEmerDate())
                .who(entity.getWho())
                .emcheck(entity.getEmcheck())
                .baljuDTO(baljuDTO)
                .memberDTO(memberDTO)
                .build();
    }

    @Override
    public EmergencyDTO createEmergency(EmergencyDTO emergencyDTO) {
        // Emergency 엔티티로 변환
        Emergency emergency = convertToEntity(emergencyDTO);

        // Emergency 엔티티 저장
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