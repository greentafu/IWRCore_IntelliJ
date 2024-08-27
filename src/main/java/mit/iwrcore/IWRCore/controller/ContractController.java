package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.MiniContractDTO;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.SaveContractDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PartnerDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;
    private final BaljuService baljuService;
    private final JodalPlanService jodalPlanService;
    private final PartnerService partnerService;
    private final MemberService memberService;

    @GetMapping("/new_contract")
    public void new_contract(@RequestParam(required = false) Long joNo, Model model){
        model.addAttribute("jodalPlan", jodalPlanService.findById(joNo));
        model.addAttribute("partner", partnerService.partnerList());
    }
    @GetMapping("/download_contract")
    public void download_contract(){
    }
    @GetMapping("/list_contract")
    public void list_contract(PageRequestDTO pageRequestDTO, PageRequestDTO2 pageRequestDTO2, Model model){
        model.addAttribute("yesPlan_list", jodalPlanService.noContract(pageRequestDTO));
//        model.addAttribute("yesPlan_list", contractService.couldContractMaterial(pageRequestDTO));
        model.addAttribute("finContract_list", baljuService.finishedContract(pageRequestDTO2));
    }
    @GetMapping("/modify_contract")
    public void modify_contract(){

    }

    @PostMapping("/saveContract")
    @ResponseBody
    public String saveContract(@RequestBody SaveContractDTO saveContractDTO){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO=(AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO=memberService.findMemberDto(authMemberDTO.getMno(), null);

        PartnerDTO partnerDTO=partnerService.findPartnerDto(Long.valueOf(saveContractDTO.getPartnerNo()), null, null);

        String person=saveContractDTO.getPerson();
        String file= saveContractDTO.getFile();

        for(MiniContractDTO miniContractDTO: saveContractDTO.getPlanData()){
            JodalPlanDTO jodalPlanDTO=jodalPlanService.findById(Long.valueOf(miniContractDTO.getJoNo()));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime=LocalDateTime.parse(miniContractDTO.getInputDate()+" 00:00:00", formatter);

            ContractDTO contractDTO=ContractDTO.builder()
                    .who(person)
                    .money(Long.valueOf(miniContractDTO.getInputCash()))
                    .conNum(Long.valueOf(miniContractDTO.getInputNum()))
                    .howDate(Long.valueOf(miniContractDTO.getInputDayNum()))
                    .conDate(localDateTime)
                    .jodalPlanDTO(jodalPlanDTO)
                    .partnerDTO(partnerDTO)
                    .memberDTO(memberDTO)
                    .build();
            contractService.createContract(contractDTO);
        }
        return "redirect:/contract/list_contract";
    }
    @PostMapping("/delete_contract")
    public void delete_contract(){

    }
}


