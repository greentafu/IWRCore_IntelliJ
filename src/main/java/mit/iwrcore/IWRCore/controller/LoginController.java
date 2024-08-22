package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthPartnerDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
public class LoginController {

    private final PartCodeService partCodeService;
    private final MaterService materService;
    private final ProCodeService proCodeService;
    private final ProductService productService;
    private final JodalPlanService jodalPlanService;

    @GetMapping("/login")
    public void login(){

    }
    @GetMapping("/checkrole")
    public String checkrole(@AuthenticationPrincipal AuthMemberDTO authMember, @AuthenticationPrincipal AuthPartnerDTO authPartner){
        if(authMember!=null) return "redirect:/main";
        else if(authPartner!=null) return "redirect:/partner/main";
        else return "redirect:/login?error";
    }
    @GetMapping("/main")
    public void main(Model model){
        model.addAttribute("newProductCount", productService.newProductCount());
        model.addAttribute("newNoneChasu", jodalPlanService.newNoneJodalChasuCount());
    }
    @GetMapping("/category")
    public void category(Model model){
        PartCodeListDTO lists=partCodeService.findListPartAll(null, null,null);
        model.addAttribute("partCodeList", lists);
        MaterCodeListDTO lists2=materService.findListMaterAll(null, null, null);
        model.addAttribute("materCodeList", lists2);
        ProCodeListDTO list3=proCodeService.findListProAll(null, null, null);
        model.addAttribute("proCodeList", list3);
    }

}
