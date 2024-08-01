package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner")
public class PartnerController {
    @GetMapping("/list_contract")
    public void list_contract(){

    }
    @GetMapping("/list_order")
    public void liat_order(){

    }
    @GetMapping("/list_invoice")
    public void list_invoice(){

    }
    @GetMapping("/add_invoice")
    public void add_invoice(){

    }
    @GetMapping("/modify_invoice")
    public void modify_invoice(){

    }
    @GetMapping("/list_return")
    public void list_return(){

    }
    @GetMapping("/list_urgent")
    public void list_urgetn(){

    }
    @GetMapping("/view_product")
    public void view_product(){

    }
    @GetMapping("/view_return")
    public void view_return(){

    }
    @GetMapping("/main")
    public void main(){

    }
}
