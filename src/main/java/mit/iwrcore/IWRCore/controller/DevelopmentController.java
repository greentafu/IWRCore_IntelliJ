package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/development")
public class DevelopmentController {
    @GetMapping("/input_dev")
    public void input_dev(){

    }
    @GetMapping("/list_dev")
    public void list_dev(){

    }
    @GetMapping("/detail_dev")
    public void detail_dev(){

    }
}
