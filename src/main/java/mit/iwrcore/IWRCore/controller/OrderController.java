package mit.iwrcore.IWRCore.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.EmergencyRepository;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.MiniJodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.AjaxDTO.SaveBaljuDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final BaljuService baljuService;
    private final ContractService contractService;
    private final MemberService memberService;
    private final JodalChasuService jodalChasuService;
    private final EmergencyService emergencyService;
    private final RequestService requestService;
    private final EmergencyRepository emergencyRepository;
    private final MaterialService materialService;
    private final ProplanService proplanService;
    @GetMapping("/download_order")
    public void download_order(){

    }
    @GetMapping("/list_order")
    public void list_order(PageRequestDTO pageRequestDTO, PageRequestDTO2 pageRequestDTO2, Model model){
        model.addAttribute("couldBalju_list", baljuService.couldBalju(pageRequestDTO));
        model.addAttribute("didBalju_list", baljuService.finBaljuPage(pageRequestDTO2));
    }
    @GetMapping("/modify_order2")
    public void modify_order2(){

    }
    @GetMapping("/new_order")
    public void new_order(@RequestParam(required = false) Long conNo, Model model){

    }
    @GetMapping("/new_order2")
    public void new_order2(@RequestParam(required = false) Long conNo, Model model){
        ContractDTO contractDTO=contractService.getContractById(conNo);
        model.addAttribute("contract_list", contractService.newOrderContract(contractDTO.getPartnerDTO().getPno()));
    }

    @PostMapping("/saveBalju")
    @ResponseBody
    public String saveBalju(@RequestBody List<SaveBaljuDTO> baljuDataList){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO=(AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO=memberService.findMemberDto(authMemberDTO.getMno(), null);

        for(SaveBaljuDTO saveBaljuDTO:baljuDataList){
            ContractDTO contractDTO=contractService.getContractById(Long.valueOf(saveBaljuDTO.getConNo()));

            List<MiniJodalChasuDTO> miniList=saveBaljuDTO.getChasuList();
            Long sum=0L;
            for(MiniJodalChasuDTO miniDTO:miniList){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime localDateTime=LocalDateTime.parse(miniDTO.getJodlaDate()+" 00:00:00", formatter);

                Long joNum=Long.valueOf(miniDTO.getJodlaNum());
                JodalChasuDTO jodalChasuDTO=jodalChasuService.getJodalChasuById(Long.valueOf(miniDTO.getJodalNo()));
                jodalChasuDTO.setJoNum(joNum);
                jodalChasuDTO.setJoDate(localDateTime);

                jodalChasuService.createJodalChasu(jodalChasuDTO);
                sum+=joNum;
            }

            BaljuDTO baljuDTO=BaljuDTO.builder()
                    .baljuWhere(saveBaljuDTO.getBaljuWhere())
                    .baljuPlz(saveBaljuDTO.getBaljuWhere())
                    .baljuNum(sum)
                    .finCheck(0L)
                    .contractDTO(contractDTO)
                    .memberDTO(memberDTO)
                    .build();
            baljuService.createBalju(baljuDTO);
        }
        return "redirect:/order/list_order";
    }
    @PostMapping("/delete_order")
    public void delete_order(){

    }
    @PostMapping("/urgent")
    public void urgent(){

    }
    @PostMapping("/createEmergency")
    public ResponseEntity<Void> createEmergency(@RequestBody EmergencyDTO emergencyDTO) {
        log.info("Received EmergencyDTO: {}", emergencyDTO);

        // 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO = memberService.findMemberDto(authMemberDTO.getMno(), null);
        Member writer = memberService.memberdtoToEntity(memberDTO);

        try {
            // Emergency 엔티티 생성 및 필수 정보 설정
            Emergency emergency = new Emergency();
            emergency.setWriter(writer);
            emergency.setEmerNum(emergencyDTO.getEmerNum());
            emergency.setEmerDate(emergencyDTO.getEmerDate());
            emergency.setWho(emergencyDTO.getWho());
            emergency.setEmcheck(emergencyDTO.getEmcheck());

            // BaljuDTO를 사용하여 Balju 엔티티 설정
            BaljuDTO baljuDTO = emergencyDTO.getBaljuDTO();
            if (baljuDTO != null && baljuDTO.getBaljuNo() != null) {
                Balju balju = baljuService.convertToEntity(baljuDTO);
                emergency.setBalju(balju);
            } else {
                log.warn("BaljuDTO is null or baljuNo is missing for EmergencyDTO: {}", emergencyDTO);
                return ResponseEntity.badRequest().build();
            }

            // Emergency 엔티티를 데이터베이스에 저장
            emergencyRepository.save(emergency);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error processing EmergencyDTO: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




}
