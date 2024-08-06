package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jago")
public class JagoController {
    @GetMapping("/list_stock")
    public void list_stock(){

    }
    @GetMapping("/list_stockM")
    public void list_stockM(){

    }
    @GetMapping("/stock")
    public void stock(){

    }
}
