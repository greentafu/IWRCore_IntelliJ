package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@RequestMapping("/material")
@RequiredArgsConstructor
@Log4j2
@Controller
public class MaterialController {

    private final MaterialService materialService;
    private final MemberService memberService;
    private final MaterService materService;
    private final BoxService boxService;

    @Autowired
    private FileService fileService;

    @GetMapping("/list_material")
    public void list_material(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("material_list", materialService.findMaterialAll(pageRequestDTO));
    }

    @GetMapping("/material")
    public void material(@RequestParam Long materCode, Model model) {
        MaterialDTO materialDTO=materialService.findM(materCode);
        model.addAttribute("material", materialDTO);
    }

    @GetMapping("/modify_material")
    public void modify_material(@RequestParam Long materCode, Model model) {
        MaterialDTO materialDTO=materialService.findM(materCode);
        model.addAttribute("material", materialDTO);
        model.addAttribute("boxList", boxService.list());
    }

    @GetMapping("/new_material")
    public void new_material(Model model) {
        model.addAttribute("boxList", boxService.list());
    }

    @PostMapping("/register")
    public String aaa(@ModelAttribute MaterialDTO materialDTO, @RequestParam(name = "box") Long box, @RequestParam(name = "materS") Long materS,
                      @RequestParam("uploadFiles") MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO = memberService.findMemberDto(authMemberDTO.getMno(), null);

        materialDTO.setMemberDTO(memberDTO);
        materialDTO.setBoxDTO(BoxDTO.builder().boxcode(1L).build()); // 박스 찾는 것 필요
        materialDTO.setMaterSDTO(materService.findMaterS(materS));

        // 파일 저장
        if (!file.isEmpty()) {
            try {
                String fileName = fileService.storeFile(file);
                materialDTO.setFile(fileName); // 파일 이름을 DTO에 추가
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/material/new_material";
            }
        }

        materialService.insertj(materialDTO);
        return "redirect:/material/list_material";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("uploadFiles") MultipartFile file) {
        try {
            String fileName = fileService.storeFile(file); // 파일 저장
            return "{\"status\":\"success\", \"fileName\":\"" + fileName + "\"}";
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"status\":\"error\", \"message\":\"파일 저장 실패\"}";
        }
    }
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName) {
        try {
            Resource resource = fileService.loadFileAsResource(fileName);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(resource.getFilename(), "UTF-8") + "\"")
                    .body(resource);
        } catch (Exception e) {
            log.error("파일 다운로드 실패", e);
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public String viewMaterial(@PathVariable Long id, Model model) {
        MaterialDTO materialDTO = materialService.findM(id);
        model.addAttribute("materialDTO", materialDTO);
        return "material"; // Thymeleaf 템플릿 파일명
    }
    @GetMapping("/deleteMaterial")
    public String deleteMaterial(@RequestParam(required = false) Long materCode){
        materialService.deleteJa(materCode);
        return "redirect:/material/list_material";
    }

}



