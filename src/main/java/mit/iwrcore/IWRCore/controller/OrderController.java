package mit.iwrcore.IWRCore.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.MiniJodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.SaveBaljuDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
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
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final BaljuService baljuService;
    private final ContractService contractService;
    private final MemberService memberService;
    private final JodalChasuService jodalChasuService;

    @GetMapping("/download_order")
    public void download_order(){

    }
    @GetMapping("/list_order")
    public void list_order(PageRequestDTO pageRequestDTO, PageRequestDTO2 pageRequestDTO2, Model model){
        model.addAttribute("couldBalju_list", baljuService.couldBalju(pageRequestDTO));
        model.addAttribute("didBalju_list", baljuService.finBaljuPage(pageRequestDTO2));
    }
    @GetMapping("/modify_order2")
    public void modify_order2(){

    }
    @GetMapping("/new_order")
    public void new_order(@RequestParam(required = false) Long conNo, Model model){

    }
    @GetMapping("/new_order2")
    public void new_order2(@RequestParam(required = false) Long conNo, Model model){
        ContractDTO contractDTO=contractService.getContractById(conNo);
        model.addAttribute("contract_list", contractService.newOrderContract(contractDTO.getPartnerDTO().getPno()));
    }

    @PostMapping("/saveBalju")
    @ResponseBody
    public String saveBalju(@RequestBody List<SaveBaljuDTO> baljuDataList){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO=(AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO=memberService.findMemberDto(authMemberDTO.getMno(), null);

        for(SaveBaljuDTO saveBaljuDTO:baljuDataList){
            ContractDTO contractDTO=contractService.getContractById(Long.valueOf(saveBaljuDTO.getConNo()));

            List<MiniJodalChasuDTO> miniList=saveBaljuDTO.getChasuList();
            Long sum=0L;
            for(MiniJodalChasuDTO miniDTO:miniList){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime localDateTime=LocalDateTime.parse(miniDTO.getJodlaDate()+" 00:00:00", formatter);

                Long joNum=Long.valueOf(miniDTO.getJodlaNum());
                JodalChasuDTO jodalChasuDTO=jodalChasuService.getJodalChasuById(Long.valueOf(miniDTO.getJodalNo()));
                jodalChasuDTO.setJoNum(joNum);
                jodalChasuDTO.setJoDate(localDateTime);

                jodalChasuService.createJodalChasu(jodalChasuDTO);
                sum+=joNum;
            }

            BaljuDTO baljuDTO=BaljuDTO.builder()
                    .baljuWhere(saveBaljuDTO.getBaljuWhere())
                    .baljuPlz(saveBaljuDTO.getBaljuWhere())
                    .baljuNum(sum)
                    .finCheck(0L)
                    .contractDTO(contractDTO)
                    .memberDTO(memberDTO)
                    .build();
            baljuService.createBalju(baljuDTO);
        }
        return "redirect:/order/list_order";
    }
}
