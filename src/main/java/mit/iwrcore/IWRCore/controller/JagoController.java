package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.BoxService;
import mit.iwrcore.IWRCore.security.service.ContractService;
import mit.iwrcore.IWRCore.security.service.MaterService;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jago")
@RequiredArgsConstructor
public class JagoController {

    private final MaterService materService;
    private final ProCodeService proCodeService;
    private final ContractService contractService;
    private final BoxService boxService;

    @GetMapping("/list_stock")
    public void list_stock(Model model){
        model.addAttribute("list", contractService.stockList());
        model.addAttribute("box_list", boxService.list());
    }
    @GetMapping("/list_stockM")
    public void list_stockM(Model model){
        model.addAttribute("list", contractService.stockList());
    }
    @GetMapping("/stock")
    public void stock(Long materCode, Model model){
        model.addAttribute("materCode", materCode);
    }
}
