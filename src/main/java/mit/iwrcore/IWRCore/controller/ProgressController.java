package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final GumsuService gumsuService;
    private final GumsuChasuService gumsuChasuService;
    private final BaljuService baljuService;

    @GetMapping("/list_progress")
    public void list_progress(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("list", gumsuChasuService.getAllGumsuChasuContract(pageRequestDTO));
    }
    @GetMapping("/add_progress")
    public void add_progress(@RequestParam(required = false) Long baljuNo, Model model){
        BaljuDTO baljuDTO =baljuService.getBaljuById(baljuNo);
        model.addAttribute("balju", baljuDTO);
        model.addAttribute("nonGumsu_list", gumsuService.getNonGumsuPartner());
    }
    @GetMapping("/modify_progress")
    public void modify_progress(){

    }
    @GetMapping("/requiring_progress")
    public void requiring_progress(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("list", gumsuService.couldGumsu(pageRequestDTO));
    }
    @PostMapping("/delete_progress")
    public void delete_progress(){

    }
}
