package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.MaterService;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/production")
@RequiredArgsConstructor
public class ProductionController {

    @GetMapping("/list_manufacture")
    public void list_manufacture() {
    }

    @GetMapping("/new_manufacture")
    public void new_manufacture() {
    }

    @GetMapping("/modify_manufacture")
    public void modify_manufacture() {
    }

    @GetMapping("/manufacture")
    public void manufacture() {
    }

    @GetMapping("/list_newProduct")
    public void list_newProduct() {
    }
    @GetMapping("/check_manufacture")
    public void check_manufacture() {
    }
}
