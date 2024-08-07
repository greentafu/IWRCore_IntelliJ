package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.dto.MaterCodeListDTO;
import mit.iwrcore.IWRCore.dto.PartCodeListDTO;
import mit.iwrcore.IWRCore.dto.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.MaterService;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final PartCodeService partCodeService;
    private final MaterService materService;
    private final ProCodeService proCodeService;

    @GetMapping("/list_progress")
    public void list_progress(Model model){
        PartCodeListDTO lists=partCodeService.findListPartAll(null, null,null);
        model.addAttribute("partCodeList", lists);
        MaterCodeListDTO lists2=materService.findListMaterAll(null, null, null);
        model.addAttribute("materCodeList", lists2);
        ProCodeListDTO list3=proCodeService.findListProAll(null, null, null);
        model.addAttribute("proCodeList", list3);
    }
    @GetMapping("/add_progress")
    public void add_progress(){

    }
    @GetMapping("/modify_progress")
    public void modify_progress(){

    }
    @GetMapping("/requiring_progress")
    public void requiring_progress(){

    }
}
