package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Request;
import mit.iwrcore.IWRCore.repository.RequestRepository;
import mit.iwrcore.IWRCore.security.dto.RequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService{

    private final RequestRepository requestRepository;
    private final ProplanService proPlanService;
    private final MaterialService materialService;
    private final MemberService memberService;


    @Override
    public Request convertToEntity(RequestDTO dto) {
        return Request.builder()
                .requstCode(dto.getRequstCode())
                .eventDate(dto.getEventDate())
                .text(dto.getText())
                .reqCheck(dto.getReqCheck())
                .line(dto.getLine())
                .proPlan(dto.getProplanDTO() != null ? proPlanService.dtoToEntity(dto.getProplanDTO()) : null) // ProplanDTO를 ProPlan으로 변환
                .material(dto.getMaterialDTO() != null ? materialService.materdtoToEntity(dto.getMaterialDTO()) : null) // MaterialDTO를 Material로 변환
                .writer(dto.getMemberDTO() != null ? memberService.memberdtoToEntity(dto.getMemberDTO()) : null) // MemberDTO를 Member로 변환
                .build();
    }

    @Override
    public RequestDTO convertToDTO(Request entity) {
        return RequestDTO.builder()
                .requstCode(entity.getRequstCode())
                .eventDate(entity.getEventDate())
                .text(entity.getText())
                .reqCheck(entity.getReqCheck())
                .line(entity.getLine())
                .proplanDTO(entity.getProPlan() != null ? proPlanService.entityToDTO(entity.getProPlan()) : null) // ProPlan을 ProplanDTO로 변환
                .materialDTO(entity.getMaterial() != null ? materialService.materTodto(entity.getMaterial()) : null) // Material을 MaterialDTO로 변환
                .memberDTO(entity.getWriter() != null ? memberService.memberTodto(entity.getWriter()) : null) // Member를 MemberDTO로 변환
                .build();
    }

    @Override
    public RequestDTO createRequest(RequestDTO requestDTO) {
        Request request = convertToEntity(requestDTO);
        Request savedRequest = requestRepository.save(request);
        return convertToDTO(savedRequest);
    }

    @Override
    public Optional<RequestDTO> getRequestById(Long id) {
        return requestRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public RequestDTO updateRequest(Long id, RequestDTO requestDTO) {
        if (!requestRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 RequestDTO를 찾을 수 없습니다.");
        }
        Request request = convertToEntity(requestDTO);
        request.setRequstCode(id); // 수정할 때 ID를 설정합니다.
        Request updatedRequest = requestRepository.save(request);
        return convertToDTO(updatedRequest);
    }

    @Override
    public void deleteRequest(Long id) {
        if (!requestRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 RequestDTO를 찾을 수 없습니다.");
        }
        requestRepository.deleteById(id);
    }

    @Override
    public List<RequestDTO> getAllRequests() {
        return requestRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}