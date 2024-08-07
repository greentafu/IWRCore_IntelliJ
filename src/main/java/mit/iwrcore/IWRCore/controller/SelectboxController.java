package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.dto.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PartLDTO;
import mit.iwrcore.IWRCore.security.dto.PartMDTO;
import mit.iwrcore.IWRCore.security.dto.PartSDTO;
import mit.iwrcore.IWRCore.security.service.PartCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/select")
@Log4j2
@RequiredArgsConstructor
public class SelectboxController {

    private final PartCodeService partCodeService;

    @GetMapping("/part")
    public PartCodeListDTO selectpart(
            @RequestParam(required = false) Long lcode,
            @RequestParam(required = false) Long mcode,
            @RequestParam(required = false) Long scode){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@"+lcode+"/"+mcode+"/"+scode);
        PartLDTO ldto=(lcode!=null)?partCodeService.findPartL(lcode):null;
        PartMDTO mdto=(mcode!=null)?partCodeService.findPartM(mcode):null;
        PartSDTO sdto=(scode!=null)?partCodeService.findPartS(scode):null;
        PartCodeListDTO list=partCodeService.findListPartAll(ldto, mdto, sdto);

        if(scode!=null){
            list.setL(partCodeService.findPartS(scode).getPartMDTO().getPartLDTO().getPartLcode());
            list.setM(partCodeService.findPartS(scode).getPartMDTO().getPartMcode());
            list.setS(scode);
        }else if(mcode!=null){
            list.setL(partCodeService.findPartM(mcode).getPartLDTO().getPartLcode());
            list.setM(mcode);
            list.setS(null);
        }else if(lcode!=null){
            list.setL(lcode);
            list.setM(null);
            list.setS(null);
        }

        log.info("####################"+list);
        return list;
    }
}
