package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ReturnsDTO;
import mit.iwrcore.IWRCore.security.dto.ShipmentDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/goodshandling")
@RequiredArgsConstructor
public class GoodsHandlingController {

    private final RequestService requestService;
    private final ShipmentService shipmentService;
    private final MemberService memberService;
    private final ReturnsService returnsService;

    @GetMapping("/list_received")
    public void list_received(PageRequestDTO requestDTO, Model model){
        model.addAttribute("list", shipmentService.pageShipment(requestDTO));
    }
    @GetMapping("/view_received")
    public void view_received(){

    }
    @GetMapping("return_received")
    public void return_received(@RequestParam Long shipNo, Model model){
        model.addAttribute("shipment", shipmentService.findShipment(shipNo));
    }
    @GetMapping("/list_request")
    public void list_request(PageRequestDTO requestDTO, Model model){
        model.addAttribute("list", requestService.requestPage(requestDTO));
    }
    @GetMapping("/view_request")
    public void view_request(){

    }
    @PostMapping("/receiveCheck")
    public String receiveCheck(@ModelAttribute ShipmentDTO shipmentDTO){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO=(AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO=memberService.findMemberDto(authMemberDTO.getMno(), null);
        Member member=memberService.memberdtoToEntity(memberDTO);
        Long shipNo=shipmentDTO.getShipNO();
        LocalDateTime now=LocalDateTime.now();
        shipmentService.updateShipmentDate(now, shipNo);
        return "redirect:/goodshandling/list_received";
    }
    @PostMapping("/receiveConfirm")
    public String receiveConfirm(@ModelAttribute ShipmentDTO shipmentDTO){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO=(AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO=memberService.findMemberDto(authMemberDTO.getMno(), null);
        Member member=memberService.memberdtoToEntity(memberDTO);
        Long shipNo=shipmentDTO.getShipNO();
        shipmentService.updateMemberCheck(member, shipNo);
        return "redirect:/goodshandling/list_received";
    }
    @PostMapping("/savereturn")
    public String savereturn(@ModelAttribute ReturnsDTO returnsDTO, @RequestParam("shipNo") Long shipNo){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO=(AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO=memberService.findMemberDto(authMemberDTO.getMno(), null);

        return "redirect:/goodshandling/list_received";
    }
}
