package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final GumsuService gumsuService;
    private final GumsuChasuService gumsuChasuService;

    @GetMapping("/list_progress")
    public void list_progress(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("list", gumsuChasuService.getAllGumsuChasuContract(pageRequestDTO));
    }
    @GetMapping("/add_progress")
    public void add_progress(){

    }
    @GetMapping("/modify_progress")
    public void modify_progress(){

    }
    @GetMapping("/requiring_progress")
    public void requiring_progress(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("list", gumsuService.couldGumsu(pageRequestDTO));
    }
}
