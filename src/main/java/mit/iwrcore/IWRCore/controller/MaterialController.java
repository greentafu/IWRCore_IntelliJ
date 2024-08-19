package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/material")
@RequiredArgsConstructor
@Log4j2
@Controller
public class MaterialController {

    private final MaterialService materialService;
    private final MemberService memberService;
    private final MaterService materService;
    private final RestTemplate restTemplate; // HTTP 요청 보내는거

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
    public String aaa(@ModelAttribute MaterialDTO materialDTO, @RequestParam(name = "box") Long box, @RequestParam(name = "box") Long materS) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO = memberService.findMemberDto(authMemberDTO.getMno(), null);

        materialDTO.setMemberDTO(memberDTO);
        materialDTO.setBoxDTO(BoxDTO.builder().boxcode(1L).build()); // 박스 찾는 것 필요
        materialDTO.setMaterSDTO(materService.findMaterS(materS));

        materialService.insertj(materialDTO);
        return "redirect:/material/list_material";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("uploadFiles") MultipartFile[] uploadFiles, Model model) {
        String url = "http://localhost:8080/uploadAjax"; // FileUpDownLoadController의 URL

        // HttpHeaders 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA);

        // 파일을 포함한 HttpEntity 생성
        MultiValueMap<String, Object> body = new org.springframework.util.LinkedMultiValueMap<>();
        for (MultipartFile file : uploadFiles) {
            body.add("uploadFiles", file.getResource());
        }

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 파일 업로드 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        model.addAttribute("message", "파일 업로드가 완료되었습니다.");
        return "redirect:/material/list_material"; // 업로드 완료 후 돌아갈 페이지
    }

    @GetMapping("/new_material")
    public String newMaterial() {
        return "new_material"; // 업로드 폼이 있는 뷰를 반환
    }
}
