package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/jodal")
@RequiredArgsConstructor
public class JodalController {

    private final JodalPlanService jodalPlanService;
    private final ContractService contractService;
    private final StructureService structureService;


    @GetMapping("/list_jodal")
    public void list_jodal(PageRequestDTO pageRequestDTO, PageRequestDTO2 pageRequestDTO2, Model model){
        model.addAttribute("nonPlan_list", jodalPlanService.nonJodalplanMaterial(pageRequestDTO));
        model.addAttribute("yesPlan_list", contractService.yesJodalplanMaterial(pageRequestDTO2));
    }
    @GetMapping("/jodal_ready")
    public void jodalReady(@RequestParam("manufactureCode") Long manuCode, Model model) {
        // manuCode를 통해 관련 StructureDTO 데이터를 조회
        List<StructureDTO> structureList = structureService.findByProduct_ManuCode(manuCode);

        // 모델에 데이터 추가
        model.addAttribute("structureList", structureList);
        model.addAttribute("manuCode", manuCode); // manuCode를 뷰에 전달


    }

    @GetMapping("/modify_jodal")
    public void modify_jodal(){
    }
}
