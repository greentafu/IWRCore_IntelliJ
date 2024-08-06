package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/progress")
public class ProgressController {
    @GetMapping("/list_progress")
    public void list_progress(){

    }
    @GetMapping("/add_progress")
    public void add_progress(){

    }
    @GetMapping("/modify_progress")
    public void modify_progress(){

    }
    @GetMapping("/requiring_progress")
    public void requiring_progress(){

    }
}
