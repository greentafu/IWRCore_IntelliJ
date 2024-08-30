package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Gumsu;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.SaveGumsuDTO;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.SaveJodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.TdData;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuChasuDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final GumsuService gumsuService;
    private final GumsuChasuService gumsuChasuService;
    private final BaljuService baljuService;
    private final MemberService memberService;

    @GetMapping("/list_progress")
    public void list_progress(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("list", gumsuChasuService.getAllGumsuChasuContract(pageRequestDTO));
    }
    @GetMapping("/add_progress")
    public void add_progress(@RequestParam(required = false) Long baljuNo, Model model){
        if(baljuNo!=null){
            BaljuDTO baljuDTO =baljuService.getBaljuById(baljuNo);
            model.addAttribute("balju", baljuDTO);
            model.addAttribute("selectedPno", baljuDTO.getContractDTO().getPartnerDTO().getPno());
            model.addAttribute("selectedName", baljuDTO.getContractDTO().getPartnerDTO().getName());
        }else{
            model.addAttribute("selectedPno", null);
            model.addAttribute("selectedName", null);
        }
        model.addAttribute("nonGumsu_list", gumsuService.getNonGumsuPartner());

    }
    @GetMapping("/modify_progress")
    public void modify_progress(){

    }
    @GetMapping("/requiring_progress")
    public void requiring_progress(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("list", gumsuService.couldGumsu(pageRequestDTO));
    }
    @PostMapping("/delete_progress")
    public void delete_progress(){

    }
    @PostMapping("/save_gumsu")
    @ResponseBody
    public void save_gumsu(@RequestBody List<SaveGumsuDTO> list){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO=(AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO=memberService.findMemberDto(authMemberDTO.getMno(), null);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for(SaveGumsuDTO saveGumsuDTO:list){
            BaljuDTO baljuDTO=baljuService.getBaljuById(Long.valueOf(saveGumsuDTO.getBaljuNo()));
            GumsuDTO gumsuDTO=GumsuDTO.builder()
                    .who(saveGumsuDTO.getPerson())
                    .make(0L)
                    .memberDTO(memberDTO)
                    .baljuDTO(baljuDTO)
                    .build();
            GumsuDTO savedGumsu=gumsuService.createGumsu(gumsuDTO);
            Gumsu gumsu=gumsuService.convertToEntity(savedGumsu);

            for(TdData tdData: saveGumsuDTO.getTdData()){
                LocalDateTime localDateTime=LocalDateTime.parse(tdData.getGumDate()+" 00:00:00", formatter);
                GumsuChasuDTO gumsuChasuDTO=GumsuChasuDTO.builder()
                        .gumsuNum(Long.valueOf(tdData.getGumNum()))
                        .gumsuDate(localDateTime)
                        .gumsuDTO(savedGumsu)
                        .memberDTO(memberDTO)
                        .build();

                gumsuChasuService.createGumsuChasu(gumsuChasuDTO, gumsu);
            }

        }
    }
}
