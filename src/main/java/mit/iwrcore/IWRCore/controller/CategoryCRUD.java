package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.service.MaterService;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import mit.iwrcore.IWRCore.security.service.ProCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class CategoryCRUD {

    private final PartCodeService partCodeService;
    private final MaterService materService;
    private final ProCodeService proCodeService;

    @GetMapping("/deleteCategory")
    public void deleteCategory(@RequestParam(required = false) Long scode, @RequestParam(required = false) String type){
        if(type.equals("Part")) partCodeService.deletePartS(scode);
        else if(type.equals("Mater")) materService.deleteMaterS(scode);
        else if(type.equals("Pro")) proCodeService.deleteProS(scode);
    }
    @GetMapping("/addCategory")
    public void addPartCategory(@RequestParam(required = false) Long sel,
                                @RequestParam(required = false) Long selectL, @RequestParam(required = false) Long inputL){
        System.out.println("############"+sel+selectL+inputL);
    }

    @GetMapping("/refreshPart")
    public PartCodeListDTO refreshPart(){
        return partCodeService.findListPartAll(null, null, null);
    }
    @GetMapping("/refreshMater")
    public MaterCodeListDTO refreshMater(){
        return materService.findListMaterAll(null, null, null);
    }
    @GetMapping("/refreshPro")
    public ProCodeListDTO refreshPro(){
        return proCodeService.findListProAll(null, null,null);
    }
}
