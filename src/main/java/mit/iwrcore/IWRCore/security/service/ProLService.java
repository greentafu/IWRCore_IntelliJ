package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.security.dto.ProLDTO;
import mit.iwrcore.IWRCore.entity.ProL;

public interface ProLService {
    Long register(ProLDTO dto);

    default ProL dtoToEntity(ProLDTO dto) {
        ProL entity= ProL.builder()
                .manuLcode(dto.getcode())
                .Lname(dto.getName())
                .build();
        return entity;
    }

}
