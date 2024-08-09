package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/development")
@RequiredArgsConstructor
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
