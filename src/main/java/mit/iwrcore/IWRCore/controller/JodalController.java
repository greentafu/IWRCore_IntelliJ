package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.JodalPlan;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.SaveJodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.JodalChasuDateDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ProPlanSturcture2DTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ProPlanSturctureDTO;
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

        model.addAttribute("nonPlan_list", jodalPlanService.nonJodalplanMaterial2(pageRequestDTO));
        model.addAttribute("yesPlan_list", contractService.yesJodalplanMaterial(pageRequestDTO2));
    }

    @GetMapping("/jodal_ready")
    public void jodalReady(@RequestParam("manufactureCode") Long manuCode, @RequestParam(required = false) Long joNo, Model model) {

        System.out.println(manuCode);
        System.out.println(joNo);

        JodalPlanDTO jodalPlanDTO = jodalPlanService.findById(joNo);
        List<ProPlanSturctureDTO> list=jodalPlanService.newJodalChasu(jodalPlanDTO.getProplanDTO().getProplanNo());
        model.addAttribute("structure_list", list);

        LocalDateTime startDate=list.get(0).getProplanDTO().getStartDate();
        LocalDateTime minusDate=startDate.minusDays(3L);
        model.addAttribute("baseDate", minusDate);
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

        System.out.println("##########################"+list);

        for(SaveJodalChasuDTO dto:list){
            JodalPlanDTO jodalPlanDTO=jodalPlanService.findById(Long.valueOf(dto.getId()));

            List<JodalChasuDTO> jodalChasuDTOs=jodalChasuService.findJCfromJP(jodalPlanDTO.getJoNo());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime l1=LocalDateTime.parse(dto.getOneDate()+" 00:00:00", formatter);
            LocalDateTime l2=LocalDateTime.parse(dto.getTwoDate()+" 00:00:00", formatter);
            LocalDateTime l3=LocalDateTime.parse(dto.getThreeDate()+" 00:00:00", formatter);

            JodalChasuDTO jodalChasuDTO1=JodalChasuDTO.builder()
                    .jcnum((jodalChasuDTOs!=null)?jodalChasuDTOs.get(0).getJcnum():null)
                    .jodalPlanDTO(jodalPlanDTO)
                    .joNum(Long.valueOf(dto.getOneNum()))
                    .joDate(l1)
                    .memberDTO(memberDTO)
                    .build();
            jodalChasuService.createJodalChasu(jodalChasuDTO1);
            JodalChasuDTO jodalChasuDTO2=JodalChasuDTO.builder()
                    .jcnum((jodalChasuDTOs!=null)?jodalChasuDTOs.get(1).getJcnum():null)
                    .jodalPlanDTO(jodalPlanDTO)
                    .joNum(Long.valueOf(dto.getTwoNum()))
                    .joDate(l2)
                    .memberDTO(memberDTO)
                    .build();
            jodalChasuService.createJodalChasu(jodalChasuDTO2);
            JodalChasuDTO jodalChasuDTO3=JodalChasuDTO.builder()
                    .jcnum((jodalChasuDTOs!=null)?jodalChasuDTOs.get(2).getJcnum():null)
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
    public void modify_jodal(Long joNo, Model model){
        JodalPlanDTO jodalPlanDTO=jodalPlanService.findById(joNo);
        model.addAttribute("jodalPlan", jodalPlanDTO);

        List<ProPlanSturctureDTO> list=jodalChasuService.modifyJodalChasu(jodalPlanDTO.getProplanDTO().getProplanNo());

        LocalDateTime startDate=list.get(0).getProplanDTO().getStartDate();
        LocalDateTime minusDate=startDate.minusDays(3L);
        model.addAttribute("baseDate", minusDate);

        // 최종적으로 보낼 list
        List<ProPlanSturcture2DTO> dtoList=new ArrayList<>();

        // 임시저장용
        int realsize=list.size();
        int tempsize=0;
        Long tempJoNo=0L;

        ProplanDTO tempProPlanDTO=null;
        StructureDTO tempStructureDTO=null;
        Long tempSumRequest=0L;
        Long tempSumShip=0L;
        JodalPlanDTO tempJodalPlanDTO=null;
        List<JodalChasuDateDTO> tempDtoList=new ArrayList<>();

        // 반복문
        for(ProPlanSturctureDTO item:list){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@"+item.getJodalChasuDTO());
            if(tempJoNo==0L) {
                tempJoNo=item.getJodalPlanDTO().getJoNo();
            }else if(tempJoNo!=item.getJodalPlanDTO().getJoNo()){
                dtoList.add(new ProPlanSturcture2DTO(tempProPlanDTO, tempStructureDTO, tempSumRequest,
                                                    tempSumShip, tempJodalPlanDTO, (tempDtoList.size()!=0)?new ArrayList<>(tempDtoList):null));
                tempDtoList.clear();
                tempJoNo=item.getJodalPlanDTO().getJoNo();
            }
            tempProPlanDTO=item.getProplanDTO();
            tempStructureDTO=item.getStructureDTO();
            tempSumRequest= item.getSumRequest();
            tempSumShip= item.getSumShip();
            tempJodalPlanDTO=item.getJodalPlanDTO();

            if(item.getJodalChasuDTO()!=null){
                Long jcnum=item.getJodalChasuDTO().getJcnum();
                Long juNum=item.getJodalChasuDTO().getJoNum();
                String joDate=item.getJodalChasuDTO().getJoDate().toString().split("T")[0];
                tempDtoList.add(new JodalChasuDateDTO(jcnum, juNum, joDate));
            }

            tempsize+=1;

            if(tempsize==realsize){
                dtoList.add(new ProPlanSturcture2DTO(tempProPlanDTO, tempStructureDTO, tempSumRequest,
                                                    tempSumShip, tempJodalPlanDTO, (tempDtoList.size()!=0)?new ArrayList<>(tempDtoList):null));
            }
        }

        model.addAttribute("structure_list", dtoList);
        System.out.println(dtoList);


    }
    @GetMapping("/delete_jodalchasu")
    public String delete_jodalchasu(@RequestParam(required = false) Long joNo){
        jodalChasuService.deleteJodalChasuByPlan(joNo);
        return "redirect:/jodal/list_jodal";
    }

}
