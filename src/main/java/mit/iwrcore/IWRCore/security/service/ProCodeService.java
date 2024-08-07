package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.ProL;
import mit.iwrcore.IWRCore.entity.ProM;
import mit.iwrcore.IWRCore.entity.ProS;
import mit.iwrcore.IWRCore.security.dto.ProLDTO;
import mit.iwrcore.IWRCore.security.dto.ProMDTO;
import mit.iwrcore.IWRCore.security.dto.ProSDTO;

import java.util.List;

public interface ProCodeService {

    // 회사 분류 삽입
    void insertProL(ProLDTO dto);
    void insertProM(ProMDTO dto);
    void insertProS(ProSDTO dto);

    // 회사 분류 삭제
    void deleteProL(Long lcode);
    void deleteProM(Long mcode);
    void deleteProS(Long scode);



    // 회사 분류 가져오기
    ProLDTO findProL(Long lcode);
    ProMDTO findProM(Long mcode);
    ProSDTO findProS(Long scode);

    // 회사 분류 리스트 가져오기
    List<ProLDTO> findListProL(ProMDTO proMDTO, ProSDTO proSDTO);
    List<ProMDTO> findListProM(ProLDTO proLDTO, ProSDTO proSDTO);
    List<ProSDTO> findListProS(ProLDTO proLDTO, ProMDTO proMDTO);






    // dto를 entity로
    default ProL proLdtoToEntity(ProLDTO dto){
        return ProL.builder().proLcode(dto.getProLcode()).Lname(dto.getLname()).build();
    }
    default ProM proMdtoToEntity(ProMDTO dto){
        return ProM.builder().proMcode(dto.getProMcode()).Mname(dto.getMname()).proL(dto.getProL()).build();
    }
    default ProS proSdtoToEntity(ProSDTO dto){
        return ProS.builder().proScode(dto.getProScode()).Sname(dto.getSname()).proM(dto.getProM()).build();
    }

    // entity를 dto로
    default ProLDTO proLTodto(ProL entity){
        return ProLDTO.builder().proLcode(entity.getProLcode()).Lname(entity.getLname()).build();
    }
    default ProMDTO

    proMTodto(ProM entity){
        return ProMDTO.builder().proMcode(entity.getProMcode()).Mname(entity.getMname()).proL(entity.getProL()).build();
    }
    default ProSDTO proSTodto(ProS entity){
        return ProSDTO.builder().proScode(entity.getProScode()).Sname(entity.getSname()).proM(entity.getProM()).build();
    }
}
