package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Balju;
import mit.iwrcore.IWRCore.entity.GumsuChasu;
import mit.iwrcore.IWRCore.entity.JodalChasu;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthPartnerDTO;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuChasuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.PartnerMainDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/partner")
@RequiredArgsConstructor
public class PartnerController {

    private final EmergencyService emergencyService;
    private final ContractService contractService;
    private final BaljuService baljuService;
    private final GumsuChasuService gumsuChasuService;
    private final JodalChasuService jodalChasuService;

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
    public void list_invoice(){

    }
    @GetMapping("/add_invoice")
    public void add_invoice(){

    }
    @GetMapping("/modify_invoice")
    public void modify_invoice(){

    }
    @GetMapping("/list_return")
    public void list_return(){

    }
    @GetMapping("/list_urgent")
    public void list_urgetn(PageRequestDTO requestDTO, Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthPartnerDTO authPartnerDTO=(AuthPartnerDTO) authentication.getPrincipal();
        requestDTO.setPno(authPartnerDTO.getPno());
        model.addAttribute("urget_list", emergencyService.getAllEmergencies(requestDTO));
    }
    @GetMapping("/view_product")
    public void view_product(){

    }
    @GetMapping("/view_return")
    public void view_return(){

    }
    @GetMapping("/main")
    public void main(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthPartnerDTO authPartnerDTO=(AuthPartnerDTO) authentication.getPrincipal();
        List<Object[]> list=gumsuChasuService.PartnerMain(authPartnerDTO.getPno());

        List<PartnerMainDTO> mainList=new ArrayList<>();

        for(Object[] objects:list){
            Set<BaljuDTO> baljuDTOSet=new HashSet<>();
            Set<JodalChasuDTO> jodalChasuDTOSet=new HashSet<>();
            Set<GumsuChasuDTO> gumsuChasuDTOSet=new HashSet<>();

            for(Object obj:objects){
                if(obj instanceof Balju) baljuDTOSet.add(baljuService.convertToDTO((Balju) obj));
                if(obj instanceof JodalChasu) jodalChasuDTOSet.add(jodalChasuService.convertToDTO((JodalChasu) obj));
                if(obj instanceof GumsuChasu) gumsuChasuDTOSet.add(gumsuChasuService.convertToDTO((GumsuChasu) obj));
            }

            List<BaljuDTO> baljuDTOList=new ArrayList<>(baljuDTOSet);
            List<JodalChasuDTO> jodalChasuDTOList=new ArrayList<>(jodalChasuDTOSet);
            List<GumsuChasuDTO> gumsuChasuDTOList=new ArrayList<>(gumsuChasuDTOSet);

            Comparator<BaljuDTO> baljuDTOComparator=Comparator.comparing(BaljuDTO::getBaljuNo);
            Comparator<JodalChasuDTO> jodalChasuDTOComparator=Comparator.comparing(JodalChasuDTO::getJcnum);
            Comparator<GumsuChasuDTO> gumsuChasuDTOComparator=Comparator.comparing(GumsuChasuDTO::getGcnum);

            baljuDTOList.sort(baljuDTOComparator);
            jodalChasuDTOList.sort(jodalChasuDTOComparator);
            gumsuChasuDTOList.sort(gumsuChasuDTOComparator);

            PartnerMainDTO partnerMainDTO=PartnerMainDTO.builder()
                    .baljuDTO(baljuDTOList.get(0))
                    .jodalChasuDTOs(jodalChasuDTOList)
                    .gumsuChasuDTOs(gumsuChasuDTOList)
                    .build();

            mainList.add(partnerMainDTO);
        }

        model.addAttribute("contract_list", mainList);

    }

}
