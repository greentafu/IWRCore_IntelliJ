package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterLDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterMDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartLDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartMDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartSDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProCodeListDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProLDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProMDTO;
import mit.iwrcore.IWRCore.security.dto.ProDTO.ProSDTO;
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
                                @RequestParam(required = false) Long selectL, @RequestParam(required = false) String inputL,
                                @RequestParam(required = false) Long selectM, @RequestParam(required = false) String inputM,
                                @RequestParam(required = false) String inputS){
        if(sel==1){
            if(selectM!=null){
                PartMDTO partMDTO=partCodeService.findPartM(selectM);
                if(inputS!=null&&inputS!=""){
                    PartSDTO partSDTO=PartSDTO.builder().partMDTO(partMDTO).Sname(inputS).build();
                    partCodeService.insertPartS(partSDTO);
                }
            }else if(selectM==null && selectL!=null){
                PartLDTO partLDTO=partCodeService.findPartL(selectL);
                if(inputM!=null&&inputM!=""){
                    PartMDTO partMDTO=PartMDTO.builder().partLDTO(partLDTO).Mname(inputM).build();
                    PartMDTO savedM=partCodeService.insertPartM(partMDTO);
                    if(inputS!=null&&inputS!=""){
                        PartSDTO partSDTO=PartSDTO.builder().partMDTO(savedM).Sname(inputS).build();
                        partCodeService.insertPartS(partSDTO);
                    }
                }
            }if(selectL==null){
                if(inputL!=null&&inputL!=""){
                    PartLDTO partLDTO=PartLDTO.builder().Lname(inputL).build();
                    PartLDTO savedL=partCodeService.insertPartL(partLDTO);
                    if(inputM!=null&&inputM!=""){
                        PartMDTO partMDTO=PartMDTO.builder().partLDTO(savedL).Mname(inputM).build();
                        PartMDTO savedM=partCodeService.insertPartM(partMDTO);
                        if(inputS!=null&&inputS!=""){
                            PartSDTO partSDTO=PartSDTO.builder().partMDTO(savedM).Sname(inputS).build();
                            partCodeService.insertPartS(partSDTO);
                        }
                    }
                }
            }
        }else if(sel==2){
            if(selectM!=null){
                ProMDTO proMDTO=proCodeService.findProM(selectM);
                if(inputS!=null&&inputS!=""){
                    ProSDTO proSDTO=ProSDTO.builder().proMDTO(proMDTO).Sname(inputS).build();
                    proCodeService.insertProS(proSDTO);
                }
            }else if(selectM==null && selectL!=null){
                ProLDTO proLDTO=proCodeService.findProL(selectL);
                if(inputM!=null&&inputM!=""){
                    ProMDTO proMDTO=ProMDTO.builder().proLDTO(proLDTO).Mname(inputM).build();
                    ProMDTO savedM=proCodeService.insertProM(proMDTO);
                    if(inputS!=null&&inputS!=""){
                        ProSDTO proSDTO=ProSDTO.builder().proMDTO(savedM).Sname(inputS).build();
                        proCodeService.insertProS(proSDTO);
                    }
                }
            }if(selectL==null){
                if(inputL!=null&&inputL!=""){
                    ProLDTO proLDTO=ProLDTO.builder().Lname(inputL).build();
                    ProLDTO savedL=proCodeService.insertProL(proLDTO);
                    if(inputM!=null&&inputM!=""){
                        ProMDTO proMDTO=ProMDTO.builder().proLDTO(savedL).Mname(inputM).build();
                        ProMDTO savedM=proCodeService.insertProM(proMDTO);
                        if(inputS!=null&&inputS!=""){
                            ProSDTO proSDTO=ProSDTO.builder().proMDTO(savedM).Sname(inputS).build();
                            proCodeService.insertProS(proSDTO);
                        }
                    }
                }
            }
        }else if(sel==3){
            if(selectM!=null){
                MaterMDTO materMDTO=materService.findMaterM(selectM);
                if(inputS!=null&&inputS!=""){
                    MaterSDTO materSDTO=MaterSDTO.builder().materMDTO(materMDTO).Sname(inputS).build();
                    materService.insertMS(materSDTO);
                }
            }else if(selectM==null && selectL!=null){
                MaterLDTO materLDTO=materService.findMaterL(selectL);
                if(inputM!=null&&inputM!=""){
                    MaterMDTO materMDTO=MaterMDTO.builder().materLDTO(materLDTO).Mname(inputM).build();
                    MaterMDTO savedM=materService.insertMM(materMDTO);
                    if(inputS!=null&&inputS!=""){
                        MaterSDTO materSDTO=MaterSDTO.builder().materMDTO(savedM).Sname(inputS).build();
                        materService.insertMS(materSDTO);
                    }
                }
            }if(selectL==null){
                if(inputL!=null&&inputL!=""){
                    MaterLDTO materLDTO=MaterLDTO.builder().lname(inputL).build();
                    MaterLDTO savedL=materService.insertML(materLDTO);
                    if(inputM!=null&&inputM!=""){
                        MaterMDTO materMDTO=MaterMDTO.builder().materLDTO(savedL).Mname(inputM).build();
                        MaterMDTO savedM=materService.insertMM(materMDTO);
                        if(inputS!=null&&inputS!=""){
                            MaterSDTO materSDTO=MaterSDTO.builder().materMDTO(savedM).Sname(inputS).build();
                            materService.insertMS(materSDTO);
                        }
                    }
                }
            }
        }
    }

    @GetMapping("/modifyCategory")
    public void modifyCategory(){

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
