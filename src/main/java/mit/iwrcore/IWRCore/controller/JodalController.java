package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jodal")
public class JodalController {
    @GetMapping("/list_jodal")
    public void list_jodal(){
    }
    @GetMapping("/jodal_ready")
    public void jodal_ready(){
    }
    @GetMapping("/modify_jodal")
    public void modify_jodal(){
    }
}
