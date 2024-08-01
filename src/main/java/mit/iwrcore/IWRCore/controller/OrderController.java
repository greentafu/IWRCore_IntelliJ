package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/download_order")
    public void download_order(){

    }
    @GetMapping("/list_order")
    public void list_order(){

    }
    @GetMapping("/modify_order2")
    public void modify_order2(){

    }
    @GetMapping("/new_order")
    public void new_order(){

    }
    @GetMapping("/new_order2")
    public void new_order2(){

    }
}
