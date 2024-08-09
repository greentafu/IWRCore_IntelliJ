package mit.iwrcore.IWRCore.controller_rest;

import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.security.service.MemberService;
import mit.iwrcore.IWRCore.security.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
@Log4j2
public class ManageController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private PartnerService partnerService;

    @PostMapping("/delete_member")
    public void delete_member(@RequestParam(required = false) Long mno){
        memberService.deleteMember(mno);
    }
    @PostMapping("/delete_partner")
    public void delete_partner(@RequestParam(required = false) Long pno){
        partnerService.deletePartner(pno);
    }
}
