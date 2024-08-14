package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;
    private final BaljuService baljuService;

    @GetMapping("/new_contract")
    public void new_contract(){
    }
    @GetMapping("/download_contract")
    public void download_contract(){
    }
    @GetMapping("/list_contract")
    public void list_contract(PageRequestDTO pageRequestDTO, PageRequestDTO2 pageRequestDTO2, Model model){
        model.addAttribute("yesPlan_list", contractService.couldContractMaterial(pageRequestDTO));
        model.addAttribute("finContract_list", baljuService.finishedContract(pageRequestDTO2));
    }
    @GetMapping("/modify_contract")
    public void modify_contract(){

    }
}

