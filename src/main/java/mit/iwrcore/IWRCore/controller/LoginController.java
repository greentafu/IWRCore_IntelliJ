package mit.iwrcore.IWRCore.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Box;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.MemberRole;
import mit.iwrcore.IWRCore.entity.Partner;
import mit.iwrcore.IWRCore.repository.BoxRepository;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.repository.PartnerRepository;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartLDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartMDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartSDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthPartnerDTO;
import mit.iwrcore.IWRCore.security.dto.RequestDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Log4j2
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final PartCodeService partCodeService;
    private final MaterService materService;
    private final ProCodeService proCodeService;
    private final ProductService productService;
    private final JodalPlanService jodalPlanService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final BoxService boxService;
    private final BoxRepository boxRepository;
    private final RequestService requestService;
    private final ShipmentService shipmentService;
    private final PartnerService partnerService;
    private final PartnerRepository partnerRepository;

    @GetMapping("/login")
    @Transactional
    public void login(){
        if(boxRepository.findAll().size()==0){
            Box box0=Box.builder().boxName("미정").boxCode(1L).build();
            Box box1=Box.builder().boxName("A창고").boxCode(2L).build();
            Box box2=Box.builder().boxName("B창고").boxCode(3L).build();
            Box box3=Box.builder().boxName("C창고").boxCode(4L).build();
            boxRepository.save(box0);
            boxRepository.save(box1);
            boxRepository.save(box2);
            boxRepository.save(box3);
        }
        if(memberRepository.findAll().size()==0){
            Member member = Member.builder()
                    .mno(1L)
                    .name("관리자")
                    .id("manager")
                    .pw("1111")
                    .password(passwordEncoder.encode("1111"))
                    .phonenumber("000-0000-0000")
                    .department("자재부서")
                    .build();
            member.changeMemberRole(MemberRole.MANAGER);
            memberRepository.save(member);
        }
        if(partnerRepository.findAll().size()==0){
            PartLDTO partLDTO=PartLDTO.builder().Lname("협력회사대분류1").build();
            PartLDTO savedL=partCodeService.insertPartL(partLDTO);
            PartMDTO partMDTO=PartMDTO.builder().Mname("협력회사중분류1").partLDTO(savedL).build();
            PartMDTO savedM=partCodeService.insertPartM(partMDTO);
            PartSDTO partSDTO=PartSDTO.builder().Sname("협력회사소분류1").partMDTO(savedM).build();
            PartSDTO savedS=partCodeService.insertPartS(partSDTO);

            Partner partner=Partner.builder()
                    .name("소속회사")
                    .registrationNumber("000-00-00000")
                    .location("경기도 수원시")
                    .type("제조업")
                    .sector("금속사출부품")
                    .ceo("ceo")
                    .telNumber("000-0000-0000")
                    .faxNumber("000-0000-0001")
                    .email("company@mail.com")
                    .contacter("담당자")
                    .contacterNumber("000-0000-0000")
                    .contacterEmail("damdang@mail.com")
                    .partS(partCodeService.partSdtoToEntity(savedS))
                    .id("company")
                    .pw("1111")
                    .password(passwordEncoder.encode("1111"))
                    .build();
            partner.setPartnerRole(MemberRole.PARTNER);
            partnerRepository.save(partner);
        }
    }
    @GetMapping("/checkrole")
    public String checkrole(@AuthenticationPrincipal AuthMemberDTO authMember, @AuthenticationPrincipal AuthPartnerDTO authPartner){
        if(authMember!=null) return "redirect:main";
        else if(authPartner!=null) return "redirect:/partner/main";
        else return "redirect:/login?error";
    }
    @GetMapping("/main")
    public void main(PageRequestDTO requestDTO, Model model){
        model.addAttribute("newProductCount", productService.newProductCount());
        model.addAttribute("newNoneChasu", jodalPlanService.newNoneJodalChasuCount());
        model.addAttribute("newShipment", shipmentService.mainShipNum());
        model.addAttribute("newRequest", requestService.mainRequestCount());

        model.addAttribute("shipment_list", shipmentService.mainShipment(requestDTO));
    }
    @GetMapping("/category")
    public void category(Model model){
        PartCodeListDTO lists=partCodeService.findListPartAll(null, null,null);
        model.addAttribute("partCodeList", lists);
        MaterCodeListDTO lists2=materService.findListMaterAll(null, null, null);
        model.addAttribute("materCodeList", lists2);
        ProCodeListDTO list3=proCodeService.findListProAll(null, null, null);
        model.addAttribute("proCodeList", list3);
    }

}


