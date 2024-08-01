package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/production")
public class ProductionController {
    @GetMapping("/list_manufacture")
    public void list_manufacture() {
    }

    @GetMapping("/new_manufacture")
    public void new_manufacture() {
    }

    @GetMapping("/modify_manufacture")
    public void modify_manufacture() {
    }

    @GetMapping("/manufacture")
    public void manufacture() {
    }

    @GetMapping("/list_newProduct")
    public void list_newProduct() {
    }

}
