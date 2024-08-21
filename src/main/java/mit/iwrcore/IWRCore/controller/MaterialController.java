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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("/material")
@RequiredArgsConstructor
@Log4j2
@Controller
public class MaterialController {

    private final MaterialService materialService;
    private final MemberService memberService;
    private final MaterService materService;

    @Autowired
    private FileService fileService;

    @GetMapping("/list_material")
    public void list_material(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("material_list", materialService.findMaterialAll(pageRequestDTO));
    }

    @GetMapping("/material")
    public void material() {
    }

    @GetMapping("/modify_material")
    public void modify_material() {
    }

    @GetMapping("/new_material")
    public void new_material() {
    }

    @PostMapping("/register")
    public String aaa(@ModelAttribute MaterialDTO materialDTO, @RequestParam(name = "box") Long box, @RequestParam(name = "box") Long materS,
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
}

    /*@PostMapping("/register")
    public String registerMaterial(
            @ModelAttribute MaterialDTO materialDTO,
            @RequestParam("uploadFile") MultipartFile uploadFile, // 단일 파일 업로드로 변경
            @RequestParam(name = "box", required = true) String box,
            @RequestParam(name = "materS", required = false) String materS,
            Model model) {

        Long boxCode = parseLongOrNull(box);
        Long materSId = parseLongOrNull(materS);

        if (boxCode == null) {
            model.addAttribute("message", "Box code is invalid.");
            return "redirect:/material/register";
        }

        if (materSId == null) {
            model.addAttribute("message", "Mater S ID is invalid.");
            return "redirect:/material/register";
        }

        // 자재 정보 저장
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO = memberService.findMemberDto(authMemberDTO.getMno(), null);

        materialDTO.setMemberDTO(memberDTO);
        materialDTO.setBoxDTO(BoxDTO.builder().boxcode(boxCode).build());
        materialDTO.setMaterSDTO(materService.findMaterS(materSId));

        // 파일 저장 및 메타데이터 저장
        String fileName = null;
        if (uploadFile != null && !uploadFile.isEmpty()) {
            try {
                fileName = UUID.randomUUID().toString() + "_" + uploadFile.getOriginalFilename();
                Path path = Paths.get(UPLOADED_FOLDER + fileName);
                Files.write(path, uploadFile.getBytes());

                // 파일 메타데이터 저장
                FileMetadata metadata = new FileMetadata();
                metadata.setFileName(fileName);
                metadata.setFilePath(path.toString());
                fileMetadataRepository.save(metadata);

            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "파일 업로드 중 오류가 발생했습니다.");
                return "redirect:/material/register";
            }
        }
        return fileName;
    }
}*/








