package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.dto.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminpartner")
@RequiredArgsConstructor
public class PartnerAdminController {

    private final PartCodeService partCodeService;

    @GetMapping("/list_partner")
    public void list_partner(Model model){
        PartCodeListDTO lists=partCodeService.findListPartAll(null, null,null);
        model.addAttribute("partCodeList", lists);
    }
    @GetMapping("/view_partner")
    public void view_partner(){

    }
}
