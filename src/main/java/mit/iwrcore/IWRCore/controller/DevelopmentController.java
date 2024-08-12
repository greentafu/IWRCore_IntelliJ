package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
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

    @GetMapping("/input_dev")
    public void input_dev(){

    }
    @GetMapping("/list_dev")
    public void list_dev(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("product_list", productService.getAllProducts(pageRequestDTO));
    }
    @GetMapping("/detail_dev")
    public void detail_dev(){

    }
}
