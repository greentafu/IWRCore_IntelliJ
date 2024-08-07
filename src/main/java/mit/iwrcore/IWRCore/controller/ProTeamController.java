package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proteam")
@RequiredArgsConstructor
public class ProTeamController {

    private final ProCodeService proCodeService;

    @GetMapping("list_pro")
    public void list_pro(Model model){
        ProCodeListDTO list3=proCodeService.findListProAll(null, null, null);
        model.addAttribute("proCodeList", list3);
    }
    @GetMapping("list_request")
    public void list_request(Model model){
        ProCodeListDTO list3=proCodeService.findListProAll(null, null, null);
        model.addAttribute("proCodeList", list3);
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
