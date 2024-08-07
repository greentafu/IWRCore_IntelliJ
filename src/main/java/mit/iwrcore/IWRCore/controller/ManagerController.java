package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.dto.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final PartCodeService partCodeService;

    @GetMapping("/list_member")
    public void list_member(){
    }
    @GetMapping("/add_member")
    public void add_member(){

    }
    @GetMapping("/modify_member")
    public void modify_member(){

    }
    @GetMapping("/list_partner")
    public void list_partner(){

    }
    @GetMapping("/add_partner")
    public void add_partner(){

    }
    @GetMapping("/modify_partner")
    public void modify_partner(){

    }
    @GetMapping("/category")
    public void category(Model model){
        PartCodeListDTO lists=partCodeService.findListPartAll(null, null,null);
        model.addAttribute("partCodeList", lists);
    }

}
