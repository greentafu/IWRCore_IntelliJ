package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Box;
import mit.iwrcore.IWRCore.repository.BoxRepository;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements BoxService {
    private final BoxRepository boxRepository;

    @Override
    public BoxDTO getBox(Long boxId){
        return entityTodto(boxRepository.getReferenceById(boxId));
    }
//    public BoxServiceImpl(BoxRepository boxRepository) {
//        this.boxRepository = boxRepository;
//    }

    @Override
    public List<BoxDTO> list() {
        List<Box> boxes = boxRepository.findAll();
        return boxes.stream()
                .map(this::boxTodto)
                .collect(Collectors.toList());
    }

    private BoxDTO entityTodto(Box box){
        return BoxDTO.builder().boxname(box.getBoxName()).boxcode(box.getBoxCode()).build();
    }
}






