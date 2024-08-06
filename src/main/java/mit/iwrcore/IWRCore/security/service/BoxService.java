package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Box;

import mit.iwrcore.IWRCore.security.dto.BoxDTO;

import java.util.List;

public interface BoxService {
    List<BoxDTO> list();

    // dto를 entity로
    default Box boxdtoToEntity(BoxDTO dto){
        return Box.builder().boxCode(dto.getBoxcode()).boxName(dto.getBoxname()).build();
    }
    // entity를 dto로
    default BoxDTO boxTodto(Box entity){
        return BoxDTO.builder().boxcode(entity.getBoxCode()).boxname(entity.getBoxName()).build();
    }
}
