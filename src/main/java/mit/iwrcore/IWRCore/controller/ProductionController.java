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
    @GetMapping("/list_newProduct")
        public void list_newProduct() {
    }

}
