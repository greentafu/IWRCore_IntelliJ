package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    @GetMapping("/list_invoice")
    public void list_invoice(){

    }
    @GetMapping("/add_invoice")
    public void add_invoice(){

    }
    @GetMapping("/modify_invoice")
    public void modify_invoice(){

    }
    @GetMapping("/view_invoice")
    public void view_invoice(){

    }
}
