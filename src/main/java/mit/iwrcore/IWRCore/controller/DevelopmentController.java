package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.MaterialService;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import mit.iwrcore.IWRCore.security.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/development")
@RequiredArgsConstructor
public class DevelopmentController {

    private final ProductService productService;
    private final MaterialService materialService;
    private final MaterialRepository materialRepository;

    @GetMapping("/input_dev")
    public void input_dev(PageRequestDTO pageRequestDTO, Model model){
        pageRequestDTO.setSize((int)materialRepository.count());
        model.addAttribute("material_list", materialService.findMaterialAll(pageRequestDTO));
    }
    @GetMapping("/list_dev")
    public void list_dev(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("product_list", productService.getAllProducts(pageRequestDTO));
    }
    @GetMapping("/detail_dev")
    public void detail_dev(){

    }
}
