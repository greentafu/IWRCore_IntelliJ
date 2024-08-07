package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.MaterService;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goodshandling")
@RequiredArgsConstructor
public class GoodsHandlingController {

    private final PartCodeService partCodeService;
    private final MaterService materService;
    private final ProCodeService proCodeService;

    @GetMapping("/list_received")
    public void list_received(Model model){
        PartCodeListDTO lists=partCodeService.findListPartAll(null, null,null);
        model.addAttribute("partCodeList", lists);
        MaterCodeListDTO lists2=materService.findListMaterAll(null, null, null);
        model.addAttribute("materCodeList", lists2);
        ProCodeListDTO list3=proCodeService.findListProAll(null, null, null);
        model.addAttribute("proCodeList", list3);
    }
    @GetMapping("/view_received")
    public void view_received(){

    }
    @GetMapping("return_received")
    public void return_received(){

    }
    @GetMapping("/list_request")
    public void list_request(Model model){
        ProCodeListDTO list3=proCodeService.findListProAll(null, null, null);
        model.addAttribute("proCodeList", list3);
    }
    @GetMapping("/view_request")
    public void view_request(){

    }
}
