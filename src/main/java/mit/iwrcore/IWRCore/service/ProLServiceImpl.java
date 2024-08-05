package mit.iwrcore.IWRCore.service;

import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.dto.ProLDTO;
import mit.iwrcore.IWRCore.entity.ProL;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProLServiceImpl implements ProLService  {

    @Override
    public Long register(ProLDTO dto) {
        log.info("DTO-----------");
        log.info(dto);

        ProL entity= dtoToEntity(dto);

        log.info(entity);

        return null;
    }
}
