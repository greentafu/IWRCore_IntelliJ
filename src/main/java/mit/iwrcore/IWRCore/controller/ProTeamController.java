package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PlanDTO;
import mit.iwrcore.IWRCore.security.dto.ProductDTO;
import mit.iwrcore.IWRCore.security.dto.ProplanDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.format.annotation.DateTimeFormat;
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
    public void list_pro(PageRequestDTO pageRequestDTO, PageRequestDTO2 pageRequestDTO2, Model model){
        model.addAttribute("product_list", productService.getNonPlanProducts(pageRequestDTO));
        model.addAttribute("proplan_list", proplanService.proplanList2(pageRequestDTO2));
    }

    @GetMapping("/list_request")
    public void list_request(PageRequestDTO requestDTO, Model model){
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

        // 데이터가 존재하는 경우 업데이트
        List<PlanDTO> existingPlans = planService.findByProductId(manuCode);
        if (!existingPlans.isEmpty()) {
            for (int i = 0; i < existingPlans.size(); i++) {
                PlanDTO plan = existingPlans.get(i);
                Long quantity = quantities.get(i);
                plan.setQuantity(quantity);
                planService.update(plan);
            }
        }

        // 데이터가 없는 경우 새로 저장
        if (lineA != null) {
            PlanDTO newPlan = PlanDTO.builder()
                    .line("A")
                    .quantity(lineA)
                    .productDTO(productService.getProductById(manuCode))
                    .build();
            planService.save(newPlan);
        }
        if (lineB != null) {
            PlanDTO newPlan = PlanDTO.builder()
                    .line("B")
                    .quantity(lineB)
                    .productDTO(productService.getProductById(manuCode))
                    .build();
            planService.save(newPlan);
        }
        if (lineC != null) {
            PlanDTO newPlan = PlanDTO.builder()
                    .line("C")
                    .quantity(lineC)
                    .productDTO(productService.getProductById(manuCode))
                    .build();
            planService.save(newPlan);
        }

        // 수정 완료 후 동일 페이지로 리디렉션
        return "redirect:/proteam/input_pro?manuCode=" + manuCode;
    }

    @PostMapping("/Psave")
    public String PlanSave(
            @RequestParam("manuCode") Long manuCode,
            @RequestParam("line") List<String> lines,
            @RequestParam("pronum") Long pronum,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, // LocalDate로 변경
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate, // LocalDate로 변경
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
                .line(String.join(", ", lines))
                .details(details)
                .memberDTO(memberDTO) // memberDTO 설정
                .build();

        // DTO를 서비스에 저장
        ProplanDTO proplanDTO=proplanService.save(dto);

        System.out.println("########################"+proplanDTO);

        jodalPlanService.saveFromProplan(proplanDTO, memberDTO);

        return "redirect:/proteam/list_pro";
    }

    @GetMapping("/input_request")
    public void input_request(){

    }
    @GetMapping("/details_plan")
    public void details_plan(){

    }
    @GetMapping("/details_request")
    public void details_request(){

    }
    @GetMapping("/modify_plan")
    public void modify_plan(){

    }
    @GetMapping("/modify_request")
    public void modify_request(){

    }

    @PostMapping("/delete_proplan")
    public void delete_proplan(){

    }
    @PostMapping("/save_request")
    public void save_request(){

    }
   }