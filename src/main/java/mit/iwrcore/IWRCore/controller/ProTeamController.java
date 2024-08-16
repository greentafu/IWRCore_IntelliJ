package mit.iwrcore.IWRCore.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PlanDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/proteam")
@RequiredArgsConstructor
@Log4j2
public class ProTeamController {

    private final ProductService productService;
    private final ProplanService proplanService;
    private final RequestService requestService;
    private final PlanService planService;

    @GetMapping("list_pro")
    public void list_pro(PageRequestDTO pageRequestDTO, PageRequestDTO2 pageRequestDTO2, Model model){
        model.addAttribute("product_list", productService.getNonPlanProducts(pageRequestDTO));
        model.addAttribute("proplan_list", proplanService.proplanList(pageRequestDTO2));
    }

    @GetMapping("list_request")
    public void list_request(PageRequestDTO requestDTO, Model model){
        model.addAttribute("list", requestService.requestPage(requestDTO));
    }
    @GetMapping("input_pro")
    public void input_pro(@RequestParam("manuCode") Long manuCode, Model model) {

        model.addAttribute("plan_list", planService.findByProductId(manuCode));

        // 제품 코드를 이용해 제품 정보를 가져옵니다.
        ProductDTO product = productService.getProductById(manuCode);

        // 가져온 제품 정보를 모델에 추가하여 뷰로 전달합니다.
        model.addAttribute("product", product);
    }

    @GetMapping("input_request")
    public void input_request(){

    }
    @GetMapping("details_plan")
    public void details_plan(){

    }
    @GetMapping("details_request")
    public void details_request(){

    }
    @GetMapping("modify_plan")
    public void modify_plan(){

    }
    @GetMapping("modify_request")
    public void modify_request(){

    }
   }