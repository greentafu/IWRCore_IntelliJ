package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.MaterService;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/progress")
@RequiredArgsConstructor
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
