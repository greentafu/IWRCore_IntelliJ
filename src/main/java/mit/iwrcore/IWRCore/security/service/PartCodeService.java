package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.dto.PartCodeListDTO;
import mit.iwrcore.IWRCore.entity.PartL;
import mit.iwrcore.IWRCore.entity.PartM;
import mit.iwrcore.IWRCore.entity.PartS;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartLDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartMDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartSDTO;

import java.util.List;

public interface PartCodeService {

    // 회사 분류 삽입
    void insertPartL(PartLDTO dto);
    void insertPartM(PartMDTO dto);
    void insertPartS(PartSDTO dto);

    // 회사 분류 삭제
    void deletePartL(Long lcode);
    void deletePartM(Long mcode);
    void deletePartS(Long scode);



    // 회사 분류 가져오기
    PartLDTO findPartL(Long lcode);
    PartMDTO findPartM(Long mcode);
    PartSDTO findPartS(Long scode);

    // 회사 분류 리스트 가져오기
    List<PartLDTO> findListPartL();
    List<PartMDTO> findListPartM(PartLDTO partLDTO, PartMDTO partMDTO, PartSDTO partSDTO);
    List<PartSDTO> findListPartS(PartLDTO partLDTO, PartMDTO partMDTO, PartSDTO partSDTO);
    PartCodeListDTO findListPartAll(PartLDTO partLDTO, PartMDTO partMDTO, PartSDTO partSDTO);





    // dto를 entity로
    default PartL partLdtoToEntity(PartLDTO dto){
        return PartL.builder().partLcode(dto.getPartLcode()).Lname(dto.getLname()).build();
    }
    default PartM partMdtoToEntity(PartMDTO dto){
        return PartM.builder().partMcode(dto.getPartMcode()).Mname(dto.getMname()).partL(partLdtoToEntity(dto.getPartLDTO())).build();
    }
    default PartS partSdtoToEntity(PartSDTO dto){
        return PartS.builder().partScode(dto.getPartScode()).Sname(dto.getSname()).partM(partMdtoToEntity(dto.getPartMDTO())).build();
    }

    // entity를 dto로
    default PartLDTO partLTodto(PartL entity){
        return PartLDTO.builder().partLcode(entity.getPartLcode()).Lname(entity.getLname()).build();
    }
    default PartMDTO partMTOdto(PartM entity){
        return PartMDTO.builder().partMcode(entity.getPartMcode()).Mname(entity.getMname()).partLDTO(partLTodto(entity.getPartL())).build();
    }
    default PartSDTO partSTodto(PartS entity){
        return PartSDTO.builder().partScode(entity.getPartScode()).Sname(entity.getSname()).partMDTO(partMTOdto(entity.getPartM())).build();
    }

}
