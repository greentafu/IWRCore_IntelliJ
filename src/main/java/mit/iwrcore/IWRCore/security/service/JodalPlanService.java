package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.JodalPlan;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.JodalPlanJodalChsuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ProPlanSturctureDTO;

import java.util.List;
import java.util.Objects;

public interface JodalPlanService {
    void saveFromProplan(ProplanDTO proplanDTO, MemberDTO memberDTO);
    void save(JodalPlanDTO dto);
    JodalPlanDTO update(JodalPlanDTO dto);
    void deleteById(Long id);
    JodalPlanDTO findById(Long id);

    // 조달차수 없는(조달계획 필요한) 자재
    PageResultDTO<JodalPlanDTO, JodalPlan> nonJodalplanMaterial(PageRequestDTO requestDTO);
    PageResultDTO<JodalPlanDTO, Object[]> nonJodalplanMaterial2(PageRequestDTO requestDTO);

    List<ProPlanSturctureDTO> newJodalChasu(Long proplanNo);


    JodalPlan dtoToEntity(JodalPlanDTO dto);
    JodalPlanDTO entityToDTO(JodalPlan entity);

    List<JodalPlanDTO> noneContractJodalPlan();

    Long newNoneJodalChasuCount();

    PageResultDTO<JodalPlanDTO, Object[]> noContract(PageRequestDTO requestDTO);

    List<JodalPlanDTO> findJodalPlanByProPlan(Long proplanNo);

    List<JodalPlanJodalChsuDTO> noneContract();


}
