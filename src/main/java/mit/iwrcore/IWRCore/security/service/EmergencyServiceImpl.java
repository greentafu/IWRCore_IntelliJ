package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Emergency;;
import mit.iwrcore.IWRCore.repository.EmergencyRepository;
import mit.iwrcore.IWRCore.security.dto.EmergencyDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return EmergencyDTO.builder()
                .emerNo(entity.getEmerNo())
                .emerNum(entity.getEmerNum())
                .emerDate(entity.getEmerDate())
                .who(entity.getWho())
                .emcheck(entity.getEmcheck())
                .baljuDTO(baljuService.convertToDTO(entity.getBalju()))
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
    public List<EmergencyDTO> getAllEmergencies() {
        return emergencyRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}