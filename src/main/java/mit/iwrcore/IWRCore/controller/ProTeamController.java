package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import mit.iwrcore.IWRCore.security.service.ProductService;
import mit.iwrcore.IWRCore.security.service.ProplanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proteam")
@RequiredArgsConstructor
public class ProTeamController {

    private final ProductService productService;
    private final ProplanService proplanService;


    @GetMapping("list_pro")
    public void list_pro(PageRequestDTO pageRequestDTO, PageRequestDTO2 pageRequestDTO2, Model model){
        model.addAttribute("product_list", productService.getNonPlanProducts(pageRequestDTO));
        model.addAttribute("proplan_list", proplanService.proplanList(pageRequestDTO2));
    }

    @GetMapping("list_request")
    public void list_request(){
    }
    @GetMapping("input_pro")
    public void input_pro(){

    }
    @GetMapping("input_request")
    public void input_request(){

    }
    @GetMapping("details_plan")
    public void details_plan(){

    }
    @GetMapping("details_request")
    public void details_request(){

    }
    @GetMapping("modify_plan")
    public void modify_plan(){

    }
    @GetMapping("modify_request")
    public void modify_request(){

    }
}
