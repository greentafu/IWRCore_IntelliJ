package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.dto.MaterCodeListDTO;
import mit.iwrcore.IWRCore.dto.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthPartnerDTO;
import mit.iwrcore.IWRCore.security.service.MaterService;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class LoginController {

    private final PartCodeService partCodeService;
    private final MaterService materService;

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
    public void main(){

    }
    @GetMapping("/category")
    public void category(Model model){
        PartCodeListDTO lists=partCodeService.findListPartAll(null, null,null);
        model.addAttribute("partCodeList", lists);
        MaterCodeListDTO lists2=materService.findListMaterAll(null, null, null);
        model.addAttribute("materCodeList", lists2);
    }
}
