package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

import java.util.List;

@Controller
@RequestMapping("/development")
@RequiredArgsConstructor
@Log4j2
public class DevelopmentController {

    private final ProductService productService;
    private final MaterialService materialService;
    private final MaterialRepository materialRepository;
    private final MemberService memberService;
    private final ProCodeService proCodeService;
    private final StructureService structureService;

    @GetMapping("/input_dev")
    public void input_dev(PageRequestDTO pageRequestDTO, Model model){
        Integer size=(int)materialRepository.count();
        pageRequestDTO.setSize((size!=null)?((size>=1)?size:1):1);
        model.addAttribute("material_list", materialService.findMaterialAll(pageRequestDTO));
    }
    @GetMapping("/list_dev")
    public void list_dev(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("product_list", productService.getAllProducts(pageRequestDTO));
    }
    @GetMapping("/detail_dev")
    public void detail_dev(){

    }
    @GetMapping("/modify_dev")
    public void modify_dev(@RequestParam Long manuCode, PageRequestDTO pageRequestDTO, Model model){
        pageRequestDTO.setSize((int)materialRepository.count());
        model.addAttribute("material_list", materialService.findMaterialAll(pageRequestDTO));
        model.addAttribute("product", productService.getProductById(manuCode));
        model.addAttribute("structure_list", structureService.findByProduct_ManuCode(manuCode));
    }
    @PostMapping("/saveProduct")
    public String saveProduct(@RequestBody SaveProductDTO saveProductDTO){
        ProductDTO productDTO=ProductDTO.builder()
                .manuCode((saveProductDTO.getManuCode()!=null)? saveProductDTO.getManuCode():null)
                .name(saveProductDTO.getProductName())
                .color(saveProductDTO.getProColor())
                .text(saveProductDTO.getProText())
                .uuid(saveProductDTO.getProFile())
                .supervisor(saveProductDTO.getPerson())
                .mater_imsi(0L)
                .mater_check(0L)
                .build();
        if(saveProductDTO.getSel()==1) productDTO.setMater_imsi(1L);

        if(saveProductDTO.getManuCode()!=null){
            List<StructureDTO> list=structureService.findByProduct_ManuCode(saveProductDTO.getManuCode());
            list.forEach(x->structureService.deleteById(x.getSno()));
        }

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
                    .productDTO(savedProductDTO)
                    .materialDTO(materialDTO)
                    .quantity(materQuantityDTO.getQuantity())
                    .build();
            structureService.save(structureDTO);
        }
        return "redirect:/development/list_dev";
    }
    @PostMapping("/delete_product")
    public void delete_product(){

    }
}
