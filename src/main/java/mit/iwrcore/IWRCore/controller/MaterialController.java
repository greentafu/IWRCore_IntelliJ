package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/material")
@RequiredArgsConstructor
@Log4j2
public class MaterialController {

    private final MaterialService materialService;
    private final MemberService memberService;
    private final MaterService materService;

    @GetMapping("/list_material")
    public void list_material(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("material_list", materialService.findMaterialAll(pageRequestDTO));
    }
    @GetMapping("/material")
    public void material(){
    }
    @GetMapping("/modify_material")
    public void modify_material(){
    }
    @GetMapping("/new_material")
    public void new_material(){
    }
    @PostMapping("/register")
    public String aaa(@ModelAttribute MaterialDTO materialDTO, @RequestParam(name = "box") Long box, @RequestParam(name = "box") Long materS){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO=(AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO=memberService.findMemberDto(authMemberDTO.getMno(), null);

        materialDTO.setMemberDTO(memberDTO);
        materialDTO.setBoxDTO(BoxDTO.builder().boxcode(1L).build()); // 박스 찾는 것 필요
        materialDTO.setMaterSDTO(materService.findMaterS(materS));

        materialService.insertj(materialDTO);
        return "redirect:/material/list_material";
    }

}
