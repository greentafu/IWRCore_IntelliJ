package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.security.dto.ProLDTO;
import mit.iwrcore.IWRCore.entity.ProL;
import mit.iwrcore.IWRCore.repository.ProLRepository;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입
public class ProLServiceImpl implements ProLService  {

    private final ProLRepository repository; //반드시 final로 선언.

    @Override
    public Long register(ProLDTO dto) {
        log.info("DTO-----------");
        log.info(dto);

        ProL entity= dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getManuLcode();
    }
}
