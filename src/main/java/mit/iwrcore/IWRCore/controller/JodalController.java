package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.JodalPlanService;
import mit.iwrcore.IWRCore.security.service.MaterService;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jodal")
@RequiredArgsConstructor
public class JodalController {

    private final JodalPlanService jodalPlanService;

    @GetMapping("/list_jodal")
    public void list_jodal(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("nonPlan_list", jodalPlanService.nonJodalplanMaterial(pageRequestDTO));
    }
    @GetMapping("/jodal_ready")
    public void jodal_ready(){
    }
    @GetMapping("/modify_jodal")
    public void modify_jodal(){
    }
}
