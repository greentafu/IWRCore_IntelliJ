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
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final PartCodeService partCodeService;
    private final MaterService materService;
    private final ProCodeService proCodeService;
    private final MemberService memberService;
    private final PartnerService partnerService;

    @GetMapping("/list_member")
    public void list_member(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("member_list", memberService.findMemberList(pageRequestDTO));
    }
    @GetMapping("/add_member")
    public void add_member(){

    }
    @GetMapping("/modify_member")
    public void modify_member(){

    }
    @GetMapping("/list_partner")
    public void list_partner(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("partner_list",partnerService.findPartnerList(pageRequestDTO));
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
        MaterCodeListDTO lists2=materService.findListMaterAll(null, null, null);
        model.addAttribute("materCodeList", lists2);
        ProCodeListDTO list3=proCodeService.findListProAll(null, null, null);
        model.addAttribute("proCodeList", list3);
    }

}
