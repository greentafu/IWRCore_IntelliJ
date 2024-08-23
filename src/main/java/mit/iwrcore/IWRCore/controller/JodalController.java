package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.JodalPlan;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.SaveJodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalPlanDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/jodal")
@Log4j2
@RequiredArgsConstructor
public class JodalController {

    private final JodalPlanService jodalPlanService;
    private final ContractService contractService;
    private final StructureService structureService;
    private final JodalChasuService jodalChasuService;
    private final MemberService memberService;


    @GetMapping("/list_jodal")
    public void list_jodal(PageRequestDTO pageRequestDTO, PageRequestDTO2 pageRequestDTO2, Model model) {

        model.addAttribute("nonPlan_list", jodalPlanService.nonJodalplanMaterial(pageRequestDTO));
        model.addAttribute("yesPlan_list", contractService.yesJodalplanMaterial(pageRequestDTO2));
    }

    @GetMapping("/jodal_ready")
    public void jodalReady(@RequestParam("manufactureCode") Long manuCode, @RequestParam(required = false) Long joNo, Model model) {
        List<StructureDTO> structureList = structureService.findByProduct_ManuCode(manuCode);
        JodalPlanDTO jodalPlanDTO = jodalPlanService.findById(joNo);


        model.addAttribute("structureList", structureList);
        model.addAttribute("manuCode", manuCode);
        model.addAttribute("joNo", joNo);


        model.addAttribute("structure_list", jodalPlanService.newJodalChasu(jodalPlanDTO.getProplanDTO().getProplanNo()));


    }

    @PostMapping("/save")
    public String saveJodalChasu(
            @RequestParam("joNo") Long joNo,
            @RequestParam(value = "quantities[0].firstQuantity", required = false) ArrayList<Integer> firstQuantities,
            @RequestParam(value = "quantities[0].firstDate", required = false) ArrayList<LocalDate> firstDates,
            @RequestParam(value = "quantities[0].secondQuantity", required = false) ArrayList<Integer> secondQuantities,
            @RequestParam(value = "quantities[0].secondDate", required = false) ArrayList<LocalDate> secondDates,
            @RequestParam(value = "quantities[0].thirdQuantity", required = false) ArrayList<Integer> thirdQuantities,
            @RequestParam(value = "quantities[0].thirdDate", required = false) ArrayList<LocalDate> thirdDates) {

        // 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO = memberService.findMemberDto(authMemberDTO.getMno(), null);
        JodalPlanDTO jodalPlanDTO = jodalPlanService.findById(joNo);

        // JodalChasuDTO 리스트를 생성
        List<JodalChasuDTO> dtos = new ArrayList<>();

        // 1차 조달 정보 저장
        if (firstQuantities != null && firstDates != null) {
            for (int i = 0; i < firstQuantities.size(); i++) {
                JodalChasuDTO dto = JodalChasuDTO.builder()
                        .joNum(joNo)
                        .joDate(firstDates.get(i).atStartOfDay())
                        .memberDTO(memberDTO)
                        .jodalPlanDTO(jodalPlanDTO)
                        .build();
                dtos.add(dto);
            }
        }

        // 2차 조달 정보 저장
        if (secondQuantities != null && secondDates != null) {
            for (int i = 0; i < secondQuantities.size(); i++) {
                JodalChasuDTO dto = JodalChasuDTO.builder()
                        .joNum(joNo)
                        .joDate(secondDates.get(i).atStartOfDay())
                        .memberDTO(memberDTO)
                        .jodalPlanDTO(jodalPlanDTO)
                        .build();
                dtos.add(dto);
            }
        }

        // 3차 조달 정보 저장
        if (thirdQuantities != null && thirdDates != null) {
            for (int i = 0; i < thirdQuantities.size(); i++) {
                JodalChasuDTO dto = JodalChasuDTO.builder()
                        .joNum(joNo)
                        .joDate(thirdDates.get(i).atStartOfDay())
                        .memberDTO(memberDTO)
                        .jodalPlanDTO(jodalPlanDTO)
                        .build();
                dtos.add(dto);
            }
        }

        // 각 DTO를 저장
        for (JodalChasuDTO dto : dtos) {
            log.info("디티오에스"+dtos);

            jodalChasuService.createJodalChasu(dto);
        }
        System.out.println("성공했냐");
        // 성공적으로 처리 후 리디렉션
        return "redirect:/jodal/list_jodal";
    }

    @PostMapping("/saveJodalChasu")
    @ResponseBody
    public String saveJodalChasu(@RequestBody List<SaveJodalChasuDTO> list){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO = memberService.findMemberDto(authMemberDTO.getMno(), null);

        for(SaveJodalChasuDTO dto:list){
            JodalPlanDTO jodalPlanDTO=jodalPlanService.findById(Long.valueOf(dto.getId()));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime l1=LocalDateTime.parse(dto.getOneDate()+" 00:00:00", formatter);
            LocalDateTime l2=LocalDateTime.parse(dto.getTwoDate()+" 00:00:00", formatter);
            LocalDateTime l3=LocalDateTime.parse(dto.getThreeDate()+" 00:00:00", formatter);

            JodalChasuDTO jodalChasuDTO1=JodalChasuDTO.builder()
                    .jodalPlanDTO(jodalPlanDTO)
                    .joNum(Long.valueOf(dto.getOneNum()))
                    .joDate(l1)
                    .memberDTO(memberDTO)
                    .build();
            jodalChasuService.createJodalChasu(jodalChasuDTO1);
            JodalChasuDTO jodalChasuDTO2=JodalChasuDTO.builder()
                    .jodalPlanDTO(jodalPlanDTO)
                    .joNum(Long.valueOf(dto.getTwoNum()))
                    .joDate(l2)
                    .memberDTO(memberDTO)
                    .build();
            jodalChasuService.createJodalChasu(jodalChasuDTO2);
            JodalChasuDTO jodalChasuDTO3=JodalChasuDTO.builder()
                    .jodalPlanDTO(jodalPlanDTO)
                    .joNum(Long.valueOf(dto.getThreeNum()))
                    .joDate(l3)
                    .memberDTO(memberDTO)
                    .build();
            jodalChasuService.createJodalChasu(jodalChasuDTO3);
        }
        return "redirect:/jodal/list_jodal";
    }
    @GetMapping("/modify_jodal")
    public void modify_jodal(){

    }
}
