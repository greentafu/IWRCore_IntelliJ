package mit.iwrcore.IWRCore.controller_rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.NoneGumsuDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterLDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterMDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartLDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartMDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartSDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProLDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProMDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProSDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.BaljuJodalChasuDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/select")
@Log4j2
@RequiredArgsConstructor
public class SelectboxController {

    private final PartCodeService partCodeService;
    private final MaterService materService;
    private final ProCodeService proCodeService;
    private final ShipmentService shipmentService;
    private final PartnerService partnerService;
    private final GumsuService gumsuService;
    private final JodalPlanService jodalPlanService;
    private final MaterialService materialService;

    @GetMapping("/getPart")
    public PartCodeListDTO getPart(){
        return partCodeService.findListPartAll(null, null, null);
    }
    @GetMapping("/getPro")
    public ProCodeListDTO getPro(){
        return proCodeService.findListProAll(null, null, null);
    }
    @GetMapping("/getMater")
    public MaterCodeListDTO getMater(){
        return materService.findListMaterAll(null, null, null);
    }

    @GetMapping("/part")
    public PartCodeListDTO selectpart(
            @RequestParam(required = false) Long lcode,
            @RequestParam(required = false) Long mcode,
            @RequestParam(required = false) Long scode){
        PartLDTO ldto=(lcode!=null)?partCodeService.findPartL(lcode):null;
        PartMDTO mdto=(mcode!=null)?partCodeService.findPartM(mcode):null;
        PartSDTO sdto=(scode!=null)?partCodeService.findPartS(scode):null;
        PartCodeListDTO list=partCodeService.findListPartAll(ldto, mdto, sdto);

        if(scode!=null){
            list.setL(partCodeService.findPartS(scode).getPartMDTO().getPartLDTO().getPartLcode());
            list.setM(partCodeService.findPartS(scode).getPartMDTO().getPartMcode());
            list.setS(scode);
        }else if(mcode!=null){
            list.setL(partCodeService.findPartM(mcode).getPartLDTO().getPartLcode());
            list.setM(mcode);
            list.setS(null);
        }else if(lcode!=null){
            list.setL(lcode);
            list.setM(null);
            list.setS(null);
        }
        return list;
    }

    @GetMapping("/mater")
    public MaterCodeListDTO selectmater(
            @RequestParam(required = false) Long lcode,
            @RequestParam(required = false) Long mcode,
            @RequestParam(required = false) Long scode){
        MaterLDTO ldto=(lcode!=null)?materService.findMaterL(lcode):null;
        MaterMDTO mdto=(mcode!=null)?materService.findMaterM(mcode):null;
        MaterSDTO sdto=(scode!=null)?materService.findMaterS(scode):null;
        MaterCodeListDTO list=materService.findListMaterAll(ldto, mdto, sdto);

        if(scode!=null){
            list.setL(materService.findMaterS(scode).getMaterMDTO().getMaterLDTO().getMaterLcode());
            list.setM(materService.findMaterS(scode).getMaterMDTO().getMaterMcode());
            list.setS(scode);
        }else if(mcode!=null){
            list.setL(materService.findMaterM(mcode).getMaterLDTO().getMaterLcode());
            list.setM(mcode);
            list.setS(null);
        }else if(lcode!=null){
            list.setL(lcode);
            list.setM(null);
            list.setS(null);
        }
        return list;
    }

    @GetMapping("/pro")
    public ProCodeListDTO selectpro(
            @RequestParam(required = false) Long lcode,
            @RequestParam(required = false) Long mcode,
            @RequestParam(required = false) Long scode){
        ProLDTO ldto=(lcode!=null)?proCodeService.findProL(lcode):null;
        ProMDTO mdto=(mcode!=null)?proCodeService.findProM(mcode):null;
        ProSDTO sdto=(scode!=null)?proCodeService.findProS(scode):null;
        ProCodeListDTO list=proCodeService.findListProAll(ldto, mdto, sdto);

        if(scode!=null){
            list.setL(proCodeService.findProS(scode).getProMDTO().getProLDTO().getProLcode());
            list.setM(proCodeService.findProS(scode).getProMDTO().getProMcode());
            list.setS(scode);
        }else if(mcode!=null){
            list.setL(proCodeService.findProM(mcode).getProLDTO().getProLcode());
            list.setM(mcode);
            list.setS(null);
        }else if(lcode!=null){
            list.setL(lcode);
            list.setM(null);
            list.setS(null);
        }
        return list;
    }
    @GetMapping("/getInvoicePartner")
    public List<PartnerDTO> getPartner(@RequestParam(required = false) Long shipNo){
        return shipmentService.canInvoicePartner();
    }
    @PostMapping("/selectInvoicePartner")
    public PartnerDTO selectInvoicePartner(@RequestParam(required = false) Long pno){
        return partnerService.findPartnerDto(pno, null, null);
    }
    @GetMapping("/getInvoiceShipment")
    public List<ShipmentDTO> getShipment(@RequestParam(required = false) Long pno){
        return shipmentService.canInvoiceShipment(pno);
    }
    @GetMapping("/noneGumsu")
    public List<NoneGumsuDTO> noneGumsu(@RequestParam(required = false) Long pno){
        List<NoneGumsuDTO> result=new ArrayList<>();
        Set<BaljuDTO> baljuDTOSet=new HashSet<>();
        Set<JodalChasuDTO> jodalChasuDTOSet=new HashSet<>();

        List<BaljuJodalChasuDTO> baljuJodalChasuDTOList=gumsuService.getNoneGumsuBalju(pno);
        for(BaljuJodalChasuDTO dto:baljuJodalChasuDTOList){
            baljuDTOSet.add(dto.getBaljuDTO());
            jodalChasuDTOSet.add(dto.getJodalChasuDTO());
            if(jodalChasuDTOSet.size()==3){
                BaljuDTO baljuDTO=baljuDTOSet.stream().toList().get(0);
                List<JodalChasuDTO> jodalChasuDTOList=jodalChasuDTOSet.stream().toList();
                List<JodalChasuDTO> sortedJC=jodalChasuDTOList.stream().sorted(Comparator.comparing(JodalChasuDTO::getJcnum)).toList();
                NoneGumsuDTO noneGumsuDTO= NoneGumsuDTO.builder().baljuDTO(baljuDTO).jodalChasuDTOs(sortedJC).build();
                result.add(noneGumsuDTO);
                baljuDTOSet.clear();
                jodalChasuDTOSet.clear();
            }
        }
        return result;
    }

    @GetMapping("/anyPartner")
    public PartnerDTO anyPartner(@RequestParam(required = false) Long pno){
        return partnerService.findPartnerDto(pno, null, null);
    }

    @GetMapping("/noneContractJodalPlan")
    public List<JodalPlanDTO> noneContractJodalPlan(){
        return jodalPlanService.noneContractJodalPlan();
    }

    @GetMapping("/materialList")
    public List<MaterialDTO> materialList(){
        return materialService.materialList();
    }
}
