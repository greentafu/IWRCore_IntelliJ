package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.RequestDTO;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import mit.iwrcore.IWRCore.security.service.PartnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminpartner")
@RequiredArgsConstructor
public class PartnerAdminController {

    private final PartnerService partnerService;

    @GetMapping("/list_partner")
    public void list_partner(PageRequestDTO requestDTO, Model model){
        model.addAttribute("list", partnerService.findPartnerList(requestDTO));
    }
    @GetMapping("/view_partner")
    public void view_partner(Long pno, Model model){
        model.addAttribute("partner", partnerService.findPartnerDto(pno, null, null));
    }
}
