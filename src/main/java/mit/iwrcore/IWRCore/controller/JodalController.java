package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.MaterService;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jodal")
@RequiredArgsConstructor
public class JodalController {

    private final PartCodeService partCodeService;
    private final MaterService materService;
    private final ProCodeService proCodeService;

    @GetMapping("/list_jodal")
    public void list_jodal(Model model){
        MaterCodeListDTO lists2=materService.findListMaterAll(null, null, null);
        model.addAttribute("materCodeList", lists2);
        ProCodeListDTO list3=proCodeService.findListProAll(null, null, null);
        model.addAttribute("proCodeList", list3);
    }
    @GetMapping("/jodal_ready")
    public void jodal_ready(){
    }
    @GetMapping("/modify_jodal")
    public void modify_jodal(){
    }
}
