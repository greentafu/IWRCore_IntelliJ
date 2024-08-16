package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.MaterQuantityDTO;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.SaveProductDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProSDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/production")
@RequiredArgsConstructor
public class ProductionController {

    private final ProductService productService;
    private final MaterialService materialService;
    private final MaterialRepository materialRepository;
    private final StructureService structureService;
    private final MemberService memberService;
    private final ProCodeService proCodeService;

    @GetMapping("/list_manufacture")
    public void list_manufacture(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("product_list", productService.getCheckProducts(pageRequestDTO));
    }

    @GetMapping("/new_manufacture")
    public void new_manufacture() {
    }

    @GetMapping("/modify_manufacture")
    public void modify_manufacture() {
    }

    @GetMapping("/manufacture")
    public void manufacture() {
    }

    @GetMapping("/list_newProduct")
    public void list_newProduct(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("product_list", productService.getNonCheckProducts(pageRequestDTO));
    }
    @GetMapping("/check_manufacture")
    public void check_manufacture(@RequestParam Long manuCode, PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setSize((int)materialRepository.count());
        model.addAttribute("material_list", materialService.findMaterialAll(pageRequestDTO));
        model.addAttribute("product", productService.getProductById(manuCode));
        model.addAttribute("structure_list", structureService.findByProduct_ManuCode(manuCode));
    }
    @PostMapping("/finalSaveProduct")
    public String saveProduct(@RequestBody SaveProductDTO saveProductDTO){
        ProductDTO productDTO=ProductDTO.builder()
                .manuCode(saveProductDTO.getManuCode())
                .name(saveProductDTO.getProductName())
                .color(saveProductDTO.getProColor())
                .text(saveProductDTO.getProText())
                .uuid(saveProductDTO.getProFile())
                .supervisor(saveProductDTO.getPerson())
                .mater_imsi(1L)
                .mater_check(1L)
                .build();

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO=(AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO=memberService.findMemberDto(authMemberDTO.getMno(), null);
        productDTO.setMemberDTO(memberDTO);

        ProSDTO proSDTO=proCodeService.findProS(saveProductDTO.getSelectProS());
        productDTO.setProSDTO(proSDTO);

        ProductDTO savedProductDTO=productService.addProduct(productDTO);

        for(MaterQuantityDTO materQuantityDTO:saveProductDTO.getMaterQuantityDTOs()){
            MaterialDTO materialDTO=materialService.findM(materQuantityDTO.getCode());

            StructureDTO structureDTO=StructureDTO.builder()
                    .sno(materQuantityDTO.getSno())
                    .productDTO(savedProductDTO)
                    .materialDTO(materialDTO)
                    .quantity(materQuantityDTO.getQuantity())
                    .build();
            structureService.save(structureDTO);
        }
        return "redirect:/production/list_newProduct";
    }
}
