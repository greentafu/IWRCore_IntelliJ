package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminpartner")
public class PartnerAdminController {
    @GetMapping("/list_partner")
    public void list_partner(){

    }
    @GetMapping("/view_partner")
    public void view_partner(){

    }
}
