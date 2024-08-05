package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.PartL;
import mit.iwrcore.IWRCore.entity.PartM;
import mit.iwrcore.IWRCore.entity.PartS;
import mit.iwrcore.IWRCore.security.dto.PartLDTO;
import mit.iwrcore.IWRCore.security.dto.PartMDTO;
import mit.iwrcore.IWRCore.security.dto.PartSDTO;

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
    PartL findPartL(Long lcode);
    PartM findPartM(Long mcode);
    PartS findPartS(Long scode);

    // 회사 분류 리스트 가져오기
    List<PartL> findListPartL(PartM partM, PartS partS);
    List<PartM> findListPartM(PartL partL, PartS partS);
    List<PartS> findListPartS(PartL partL, PartS partS);






    // dto를 entity로
    default PartL partLdtoToEntity(PartLDTO dto){
        return PartL.builder().partLcode(dto.getPartLcode()).Lname(dto.getLname()).build();
    }
    default PartM partMdtoToEntity(PartMDTO dto){
        return PartM.builder().partMcode(dto.getPartMcode()).Mname(dto.getMname()).partL(dto.getPartL()).build();
    }
    default PartS partSdtoToEntity(PartSDTO dto){
        return PartS.builder().partScode(dto.getPartScode()).Sname(dto.getSname()).partM(dto.getPartM()).build();
    }

    // entity를 dto로
    default PartLDTO partLTodto(PartL entity){
        return PartLDTO.builder().partLcode(entity.getPartLcode()).Lname(entity.getLname()).build();
    }
    default PartMDTO partMTOdto(PartM entity){
        return PartMDTO.builder().partMcode(entity.getPartMcode()).Mname(entity.getMname()).partL(entity.getPartL()).build();
    }
    default PartSDTO partSTodto(PartS entity){
        return PartSDTO.builder().partScode(entity.getPartScode()).Sname(entity.getSname()).partM(entity.getPartM()).build();
    }

}
