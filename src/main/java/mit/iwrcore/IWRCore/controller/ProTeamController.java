package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proteam")
public class ProTeamController {
    @GetMapping("list_pro")
    public void list_pro(){

    }
    @GetMapping("list_request")
    public void list_request(){

    }
    @GetMapping("input_pro")
    public void input_pro(){

    }
    @GetMapping("input_request")
    public void input_request(){

    }
    @GetMapping("details_plan")
    public void details_plan(){

    }
    @GetMapping("details_request")
    public void details_request(){

    }
    @GetMapping("modify_plan")
    public void modify_plan(){

    }
    @GetMapping("modify_request")
    public void modify_request(){

    }
}
