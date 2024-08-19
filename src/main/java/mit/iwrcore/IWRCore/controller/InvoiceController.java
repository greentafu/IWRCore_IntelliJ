package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Shipment;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ShipmentDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final ShipmentService shipmentService;
    private final PartnerService partnerService;
    private final MaterialService materialService;

    @GetMapping("/list_invoice")
    public void list_invoice(PageRequestDTO requestDTO, PageRequestDTO2 requestDTO2, Model model){
        model.addAttribute("none_list", shipmentService.noneInvoiceShipment(requestDTO));
    }
    @GetMapping("/add_invoice")
    public void add_invoice(@RequestParam(required = false) Long shipNO, Model model){
        // 회사 정보 전달(고정)
        model.addAttribute("company", partnerService.findPartnerDto(19L, null, null));
//        model.addAttribute("material", materialService.materialList());
//
//        if(shipNO==null){
////            Shipment shipment=shipmentService.findShipmentEntity(shipNO);
////            Long pno=shipment.getBalju().getContract().getPartner().getPno();
//            List<ShipmentDTO> shipmentDTOs=shipmentService.canInvoiceShipment();
//            model.addAttribute("shipment_list", shipmentDTOs);
//            model.addAttribute("partner_list", shipmentService.canInvoicePartner());
//        }





    }
    @GetMapping("/modify_invoice")
    public void modify_invoice(){

    }
    @GetMapping("/view_invoice")
    public void view_invoice(){

    }
}
