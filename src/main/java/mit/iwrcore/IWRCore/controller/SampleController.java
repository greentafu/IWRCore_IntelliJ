package mit.iwrcore.IWRCore.controller;

import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {
    @GetMapping("/all")
    public void exAll(){
        log.info("exAll.............");
    }
    @GetMapping("/member")
    public void member(@AuthenticationPrincipal AuthMemberDTO clubAuthMember){
        log.info("member.............");

        log.info("---------------------");
        log.info(clubAuthMember);
    }
    @GetMapping("/admin")
    public void admin(){
        log.info("admin.............");
    }
}
