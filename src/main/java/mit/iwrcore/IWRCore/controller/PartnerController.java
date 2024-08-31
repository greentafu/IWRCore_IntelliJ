package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthPartnerDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.PartnerMainDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.QuantityDateDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/partner")
@RequiredArgsConstructor
@Log4j2
public class PartnerController {

    private final EmergencyService emergencyService;
    private final ContractService contractService;
    private final BaljuService baljuService;
    private final GumsuChasuService gumsuChasuService;
    private final JodalChasuService jodalChasuService;
    private final ReturnsService returnsService;
    private final GumsuService gumsuService;
    private final ShipmentService shipmentService;

    @GetMapping("/list_contract")
    public void list_contract(PageRequestDTO requestDTO, Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthPartnerDTO authPartnerDTO=(AuthPartnerDTO) authentication.getPrincipal();
        requestDTO.setPno(authPartnerDTO.getPno());
        model.addAttribute("contract_list", contractService.partnerContractList(requestDTO));
    }
    @GetMapping("/list_order")
    public void liat_order(PageRequestDTO requestDTO, Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthPartnerDTO authPartnerDTO=(AuthPartnerDTO) authentication.getPrincipal();
        requestDTO.setPno(authPartnerDTO.getPno());
        model.addAttribute("balju_list", baljuService.partnerBaljuList(requestDTO));
    }
    @GetMapping("/list_invoice")
    public void list_invoice(PageRequestDTO requestDTO, Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthPartnerDTO authPartnerDTO=(AuthPartnerDTO) authentication.getPrincipal();
        requestDTO.setPno(authPartnerDTO.getPno());
        model.addAttribute("invoice_list", shipmentService.partnerInvoicePage(requestDTO));
    }
    @GetMapping("/list_return")
    public void list_return(PageRequestDTO requestDTO, Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthPartnerDTO authPartnerDTO=(AuthPartnerDTO) authentication.getPrincipal();
        model.addAttribute("list", returnsService.getReturnPage(requestDTO, authPartnerDTO.getPno()));
    }
    @GetMapping("/list_urgent")
    public void list_urgetn(PageRequestDTO requestDTO, Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthPartnerDTO authPartnerDTO=(AuthPartnerDTO) authentication.getPrincipal();
        requestDTO.setPno(authPartnerDTO.getPno());
        model.addAttribute("urget_list", emergencyService.getAllEmergencies(requestDTO));
    }
    @GetMapping("/view_product")
    public void view_product(@RequestParam Long baljuNo, Model model){
        BaljuDTO baljuDTO=baljuService.getBaljuById(baljuNo);

        List<QuantityDateDTO> jodalList=jodalChasuService.partnerMainJodal(
                baljuDTO.getContractDTO().getJodalPlanDTO().getJoNo(),
                baljuDTO.getRegDate(),
                gumsuService.getQuantityMake(baljuDTO.getBaljuNo()));
        List<QuantityDateDTO> gumsuList=gumsuChasuService.partnerMainGumsu(baljuDTO.getBaljuNo());

        List<EmergencyDTO> emergencyDTOs=emergencyService.getEmergencyByBalju(baljuDTO.getBaljuNo());
        List<ReturnsDTO> returnsDTOs=returnsService.getReturnsList(baljuDTO.getBaljuNo());
        List<ShipmentDTO> shipmentDTOs=shipmentService.getShipmentByBalju(baljuNo);

        PartnerMainDTO partnerMainDTO=PartnerMainDTO.builder()
                .baljuDTO(baljuDTO)
                .jodalList(jodalList)
                .gumsuList(gumsuList)
                .build();

        Long totalReturn=0L;
        Long totalShipment=0L;

        if(emergencyDTOs!=null){
            for(EmergencyDTO emergencyDTO:emergencyDTOs){
                if(emergencyDTO.getEmcheck()==0) partnerMainDTO.setEmergency(emergencyDTO.getEmerNo());
            }
        }
        if(returnsDTOs!=null) {
            for(ReturnsDTO returnsDTO:returnsDTOs){
                if(returnsDTO.getReturnCheck()==0) partnerMainDTO.setReturns(returnsDTO.getReNO());
                totalReturn+=returnsDTO.getShipmentDTO().getShipNum();
            }
        }
        if(shipmentDTOs!=null){
            for(ShipmentDTO shipmentDTO:shipmentDTOs){
                totalShipment+=shipmentDTO.getShipNum();
            }
        }
        partnerMainDTO.setTotalReturn(totalReturn);
        partnerMainDTO.setTotalShipment(totalShipment);

        model.addAttribute("list", partnerMainDTO);
    }
    @GetMapping("/view_return")
    public void view_return(@RequestParam Long reNO, Model model){
        model.addAttribute("returns", returnsService.getDetailReturn(reNO));
    }
    @GetMapping("/main")
    public void main(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthPartnerDTO authPartnerDTO=(AuthPartnerDTO) authentication.getPrincipal();

        List<PartnerMainDTO> mainList=new ArrayList<>();

        List<BaljuDTO> baljuDTOList=baljuService.partListBalju(authPartnerDTO.getPno());
        for(BaljuDTO baljuDTO:baljuDTOList){
            List<QuantityDateDTO> jodalList=jodalChasuService.partnerMainJodal(
                    baljuDTO.getContractDTO().getJodalPlanDTO().getJoNo(),
                    baljuDTO.getRegDate(),
                    gumsuService.getQuantityMake(baljuDTO.getBaljuNo()));
            List<QuantityDateDTO> gumsuList=gumsuChasuService.partnerMainGumsu(baljuDTO.getBaljuNo());

            List<EmergencyDTO> emergencyDTOs=emergencyService.getEmergencyByBalju(baljuDTO.getBaljuNo());
            List<ReturnsDTO> returnsDTOs=returnsService.getReturnsList(baljuDTO.getBaljuNo());
            List<ShipmentDTO> shipmentDTOs=shipmentService.getShipmentByBalju(baljuDTO.getBaljuNo());

            PartnerMainDTO partnerMainDTO=PartnerMainDTO.builder()
                    .baljuDTO(baljuDTO)
                    .jodalList(jodalList)
                    .gumsuList((gumsuList.size()!=0)?gumsuList:null)
                    .build();
            if(emergencyDTOs!=null) emergencyDTOs.forEach(x->{
                if(x.getEmcheck()==0) partnerMainDTO.setEmergency(x.getEmerNo());
            });
            if(returnsDTOs!=null) returnsDTOs.forEach(x->{
                if(x.getReturnCheck()==0) partnerMainDTO.setReturns(x.getReNO());
            });

            mainList.add(partnerMainDTO);
        }

        model.addAttribute("main_list", mainList);
    }
    @PostMapping("/release")
    public String release(@ModelAttribute ShipmentDTO shipmentDTO, @RequestParam Long baljuNo, RedirectAttributes attr){
        ShipmentDTO saveShipment=shipmentDTO;
        shipmentDTO.setReceiveCheck(0L);
        shipmentDTO.setBaljuDTO(baljuService.getBaljuById(baljuNo));
        shipmentService.createShipment(saveShipment);
        attr.addAttribute("baljuNo", baljuNo);
        return "redirect:/partner/view_product";
    }
    @PostMapping("/addQuantity")
    public String addQuantity(@RequestParam Long baljuNo, @RequestParam Long quantity, RedirectAttributes attr){
        if(quantity>0){
            GumsuDTO gumsuDTO=gumsuService.getGumsuById(baljuNo);
            gumsuDTO.setMake(gumsuDTO.getMake()+quantity);
            gumsuService.createGumsu(gumsuDTO);
        }
        attr.addAttribute("baljuNo", baljuNo);
        return "redirect:/partner/view_product";
    }
    @PostMapping("/returnCheck")
    public String returnCheck(@RequestParam Long reNo){
        returnsService.addReturnCheck(reNo);
        return "redirect:/partner/list_return";
    }
}
