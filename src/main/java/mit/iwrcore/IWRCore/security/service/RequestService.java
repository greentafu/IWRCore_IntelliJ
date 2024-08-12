package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Request;
import mit.iwrcore.IWRCore.security.dto.RequestDTO;

import java.util.List;
import java.util.Optional;

public interface RequestService {
    Request convertToEntity(RequestDTO dto);

    RequestDTO convertToDTO(Request entity);

    RequestDTO createRequest(RequestDTO requestDTO);
    Optional<RequestDTO> getRequestById(Long id);
    RequestDTO updateRequest(Long id, RequestDTO requestDTO);
    void deleteRequest(Long id);
    List<RequestDTO> getAllRequests();
}
