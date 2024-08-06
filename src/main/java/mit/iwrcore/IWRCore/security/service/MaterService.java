package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterLDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterMDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;


import java.util.List;

public interface MaterService {
    // 회사 분류 삽입/수정
    void insertML(MaterLDTO dto);
    void insertMM(MaterMDTO dto);
    void insertMS(MaterSDTO dto);

    // 회사 분류 삭제
    void deleteMaterL(Long lcode);
    void deleteMaterM(Long mcode);
    void deleteMaterS(Long scode);


    // 회사 분류 가져오기
    MaterLDTO findMaterL(Long lcode);
    MaterMDTO findMaterM(Long mcode);
    MaterSDTO findMaterS(Long scode);
    // 회사 분류 리스트 가져오기
    List<MaterLDTO> findListPartL(MaterMDTO materMDTO, MaterSDTO materSDTO);
    List<MaterMDTO> findListPartM(MaterLDTO materLDTO, MaterSDTO MaterSDTO);
    List<MaterSDTO>  findListPartS(MaterLDTO materLDTO, MaterMDTO materMDTO);


    // dto를 entity로
    default MaterL materLdtoToEntity(MaterLDTO dto){
        return MaterL.builder().materLcode(dto.getMaterLcode()).Lname(dto.getLname()).build();
    }
    default MaterM materMdtoToEntity(MaterMDTO dto){
        return MaterM.builder().materMcode(dto.getMaterMcode()).Mname(dto.getMname()).materL(dto.getMaterL()).build();
    }
    default MaterS materSdtoToEntity(MaterSDTO dto){
        return MaterS.builder().materScode(dto.getMaterScode()).Sname(dto.getSname()).materM(dto.getMaterm()).build();
    }

    // entity를 dto로
    default MaterLDTO materLTodto(MaterL entity){
        return MaterLDTO.builder().materLcode(entity.getMaterLcode()).lname(entity.getLname()).build();
    }
    default MaterMDTO materMTodto(MaterM entity){
        return MaterMDTO.builder().materMcode(entity.getMaterMcode()).Mname(entity.getMname()).materL(entity.getMaterL()).build();
    }
    default MaterSDTO materSTodto(MaterS entity){
        return MaterSDTO.builder().materScode(entity.getMaterScode()).Sname(entity.getSname()).materm(entity.getMaterM()).build();
    }
}