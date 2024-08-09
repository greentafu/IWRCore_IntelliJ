package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.MaterService;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/material")
@RequiredArgsConstructor
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
