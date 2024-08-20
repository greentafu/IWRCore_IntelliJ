package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Invoice;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Shipment;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.InvoiceTextDTO;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.SaveInvoiceDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.InvoiceDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ShipmentDTO;
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
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final ShipmentService shipmentService;
    private final PartnerService partnerService;
    private final MemberService memberService;
    private final InvoiceService invoiceService;

    @GetMapping("/list_invoice")
    public void list_invoice(PageRequestDTO requestDTO, PageRequestDTO2 requestDTO2, Model model){
        model.addAttribute("none_list", shipmentService.noneInvoiceShipment(requestDTO));
        model.addAttribute("fin_list", shipmentService.pageFinInvoice(requestDTO2));
    }
    @GetMapping("/add_invoice")
    public void add_invoice(@RequestParam(required = false) Long shipNO, Model model){
        // 회사 정보 전달(고정)
        model.addAttribute("company", partnerService.findPartnerDto(19L, null, null));
        if(shipNO!=null){
            Shipment shipment=shipmentService.findShipmentEntity(shipNO);
            Long pno=shipment.getBalju().getContract().getPartner().getPno();
            model.addAttribute("selectedPartner", pno);
            model.addAttribute("selectedShipNo", shipNO);
        }

    }
    @GetMapping("/modify_invoice")
    public void modify_invoice(){

    }
    @GetMapping("/view_invoice")
    public void view_invoice(){

    }

    @PostMapping("/saveInvoice")
    public String saveInvoice(@RequestBody SaveInvoiceDTO saveInvoiceDTO){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO=(AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO=memberService.findMemberDto(authMemberDTO.getMno(), null);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime=LocalDateTime.parse(saveInvoiceDTO.getWriteDate()+" 00:00:00", formatter);

        InvoiceDTO invoiceDTO=InvoiceDTO.builder()
                .plz(saveInvoiceDTO.getRadio())
                .dateCreated(localDateTime)
                .text(saveInvoiceDTO.getText())
                .cash((saveInvoiceDTO.getCash()!=null)? saveInvoiceDTO.getCash() : null)
                .cheque((saveInvoiceDTO.getCheque()!=null)? saveInvoiceDTO.getCheque() : null)
                .promissory((saveInvoiceDTO.getPromissory()!=null)? saveInvoiceDTO.getPromissory() : null)
                .receivable((saveInvoiceDTO.getReceivable()!=null)? saveInvoiceDTO.getReceivable() : null)
                .memberDTO(memberDTO).build();
        InvoiceDTO savedInvoiceDTO=invoiceService.createInvoice(invoiceDTO);
        Invoice invoice=invoiceService.convertToEntity(savedInvoiceDTO);

        List<InvoiceTextDTO> invoiceTextDTOs=saveInvoiceDTO.getInvoiceTextDTOs();
        for(InvoiceTextDTO invoiceTextDTO:invoiceTextDTOs){
            shipmentService.updateSHipmentInvoice(invoice, invoiceTextDTO.getShipText(), invoiceTextDTO.getShipNo());
        }

        return "redirect:/invoice/list_invoice";
    }

}
