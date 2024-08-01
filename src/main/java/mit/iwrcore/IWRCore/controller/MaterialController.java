package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/material")
public class MaterialController {
    @GetMapping("/list_material")
    public void list_material(){
    }
    @GetMapping("/material")
    public void material(){
    }
    @GetMapping("/modify_material")
    public void modify_material(){
    }
    @GetMapping("/new_material")
    public void new_material(){
    }

}
