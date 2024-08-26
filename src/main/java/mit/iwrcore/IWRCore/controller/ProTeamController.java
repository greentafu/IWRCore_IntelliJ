package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.PlanDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
;

@Controller
@RequestMapping("/proteam")
@RequiredArgsConstructor
@Log4j2
public class ProTeamController {

    private final ProductService productService;
    private final ProplanService proplanService;
    private final RequestService requestService;
    private final PlanService planService;
    private final MemberService memberService;
    private final JodalPlanService jodalPlanService;


    @GetMapping("/list_pro")
    public void list_pro(PageRequestDTO pageRequestDTO, PageRequestDTO2 pageRequestDTO2, Model model) {
        model.addAttribute("product_list", productService.getNonPlanProducts(pageRequestDTO));
        model.addAttribute("proplan_list", proplanService.proplanList(pageRequestDTO2));
    }

    @GetMapping("/list_request")
    public void list_request(PageRequestDTO requestDTO, Model model) {
        model.addAttribute("list", requestService.requestPage(requestDTO));
    }

    @GetMapping("/input_pro")
    public void input_pro(@RequestParam("manuCode") Long manuCode, Model model) {

        model.addAttribute("plan_list", planService.findByProductId(manuCode));

        // 제품 코드를 이용해 제품 정보를 가져옵니다.
        ProductDTO product = productService.getProductById(manuCode);

        // 가져온 제품 정보를 모델에 추가하여 뷰로 전달합니다.
        model.addAttribute("product", product);
    }

    @PostMapping("/save_plan")
    public String savePlan(
            @RequestParam("manuCode") Long manuCode,
            @RequestParam(name = "lineA", required = false) Long lineA,
            @RequestParam(name = "lineB", required = false) Long lineB,
            @RequestParam(name = "lineC", required = false) Long lineC,
            @RequestParam(name = "quantities", required = false) List<Long> quantities,
            @RequestParam(name = "lines", required = false) List<String> lines) {

        // 제품 정보 가져오기
        ProductDTO product = productService.getProductById(manuCode);

        // 기존 계획 업데이트
        List<PlanDTO> existingPlans = planService.findByProductId(manuCode);
        if (!existingPlans.isEmpty()) {
            for (int i = 0; i < existingPlans.size(); i++) {
                PlanDTO plan = existingPlans.get(i);
                Long quantity = quantities != null && i < quantities.size() ? quantities.get(i) : null;
                if (quantity != null) {
                    plan.setQuantity(quantity);
                    planService.update(plan);
                }
            }
        }

        // 새 계획 저장
        if (lineA != null) {
            PlanDTO newPlan = PlanDTO.builder()
                    .line("A")
                    .quantity(lineA)
                    .productDTO(product)
                    .build();
            planService.save(newPlan);
        }
        if (lineB != null) {
            PlanDTO newPlan = PlanDTO.builder()
                    .line("B")
                    .quantity(lineB)
                    .productDTO(product)
                    .build();
            planService.save(newPlan);
        }
        if (lineC != null) {
            PlanDTO newPlan = PlanDTO.builder()
                    .line("C")
                    .quantity(lineC)
                    .productDTO(product)
                    .build();
            planService.save(newPlan);
        }

        // 리디렉션
        return "redirect:/proteam/input_pro?manuCode=" + manuCode;
    }


    @PostMapping("/Psave")
    public String PlanSave(
            @RequestParam("manuCode") Long manuCode,
            @RequestParam("line") List<String> lines,
            @RequestParam("pronum") Long pronum,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam("details") String details) {

        // 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO = memberService.findMemberDto(authMemberDTO.getMno(), null);

        // DTO 객체 생성 및 설정
        ProplanDTO dto = ProplanDTO.builder()
                .productDTO(productService.getProductById(manuCode))
                .pronum(pronum)
                .startDate(startDate.atStartOfDay()) // LocalDate를 LocalDateTime으로 변환
                .endDate(endDate.atStartOfDay())     // LocalDate를 LocalDateTime으로 변환
                .line(String.join(", ", lines)) // 라인 정보를 콤마로 구분하여 설정
                .details(details)
                .memberDTO(memberDTO) // memberDTO 설정
                .build();

        // DTO를 서비스에 저장
        ProplanDTO proplanDTO = proplanService.save(dto);

        // JodalPlanService 호출
        jodalPlanService.saveFromProplan(proplanDTO, memberDTO);

        // 리디렉션
        return "redirect:/proteam/list_pro";
    }

    @GetMapping("/input_request")
    public void input_request() {

    }

    @GetMapping("/details_plan")
    public void details_plan() {

    }

    @GetMapping("/details_request")
    public void details_request() {

    }

    @GetMapping("/modify_plan")
    public void modify_plan() {

    }

    @GetMapping("/modify_request")
    public void modify_request() {

    }

    @PostMapping("/delete_proplan")
    public void delete_proplan() {

    }

    @PostMapping("/save_request")
    public void save_request() {

    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam(value = "query", defaultValue = "") String query) {
        List<ProductDTO> products;

        if (query.isEmpty() || "all".equals(query)) {
            // 모든 제품을 가져오는 메서드에서 결과를 List로 변환
            PageResultDTO<ProductDTO, Product> pageResult = productService.getAllProducts(new PageRequestDTO());
            products = pageResult.getDtoList();
        } else {
            products = productService.searchProducts(query); // 검색 결과를 가져오는 메서드
        }

        return ResponseEntity.ok(products);
    }


}