package mit.iwrcore.IWRCore.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.dto.PartCodeDTO;
import mit.iwrcore.IWRCore.security.dto.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.AuthPartnerDTO;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class LoginController {

    private final PartCodeService partCodeService;

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
    public void category(Model model, PartCodeDTO partCodeDTO){
        model.addAttribute("partLCode", partCodeService.findListPartL(partCodeDTO.getMdto(), partCodeDTO.getSdto()));
        model.addAttribute("partMCode", partCodeService.findListPartM(partCodeDTO.getLdto(), partCodeDTO.getSdto()));
        model.addAttribute("partSCode", partCodeService.findListPartS(partCodeDTO.getLdto(), partCodeDTO.getMdto()));
    }
}
