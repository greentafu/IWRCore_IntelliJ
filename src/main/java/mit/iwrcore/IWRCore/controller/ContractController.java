package mit.iwrcore.IWRCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contract")
public class ContractController {
    @GetMapping("/new_contract")
    public void new_contract(){
    }
    @GetMapping("/download_contract")
    public void download_contract(){
    }
    @GetMapping("/list_contract")
    public void list_contract(){

    }
    @GetMapping("/modify_contract")
    public void modify_contract(){

    }
}

