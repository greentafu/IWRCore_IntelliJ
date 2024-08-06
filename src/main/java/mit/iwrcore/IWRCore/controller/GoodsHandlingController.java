package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goodshandling")
public class GoodsHandlingController {
    @GetMapping("/list_received")
    public void list_received(){

    }
    @GetMapping("/view_received")
    public void view_received(){

    }
    @GetMapping("return_received")
    public void return_received(){

    }
    @GetMapping("/list_request")
    public void list_request(){

    }
    @GetMapping("/view_request")
    public void view_request(){

    }
}
