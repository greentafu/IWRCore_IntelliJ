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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RequestMapping("/material")
@RequiredArgsConstructor
@Log4j2
@Controller
public class MaterialController {

    private final MaterialService materialService;
    private final MemberService memberService;
    private final MaterService materService;
/*
    private final RestTemplate restTemplate; // HTTP 요청 보내는거
    private final FileMetadataRepository fileMetadataRepository;

    private static final String UPLOADED_FOLDER = "uploaded/";

    private Long parseLongOrNull(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }*/

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
    public String registerMaterial(@ModelAttribute MaterialDTO materialDTO,
                                   @RequestParam("uploadFiles") MultipartFile file,
                                   @RequestParam("box") Long box,
                                   @RequestParam("materS") Long materS,
                                   Model model) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
            MemberDTO memberDTO = memberService.findMemberDto(authMemberDTO.getMno(), null);

            materialDTO.setMemberDTO(memberDTO);
            materialDTO.setBoxDTO(BoxDTO.builder().boxcode(box).build());
            materialDTO.setMaterSDTO(materService.findMaterS(materS));

            // 파일 업로드 처리
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\webapp";
            File directory = new File(projectPath);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);

            // DTO에 파일 정보 설정
            materialDTO.setFile(fileName);
            materialDTO.setUuid("/webapp/" + fileName);

            // 서비스 메서드 호출
            materialService.upload(materialDTO, file);
            materialService.insertj(materialDTO);

            model.addAttribute("message", "파일 업로드 및 자재 등록이 완료되었습니다.");
        } catch (Exception e) {
            model.addAttribute("message", "파일 업로드 또는 자재 등록 중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return "redirect:/material/list_material";
    }
}

    /*@PostMapping("/register")
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
    public String uploadMaterial(@ModelAttribute Material material,
                                 @RequestParam("uploadFiles") MultipartFile file,
                                 Model model) {
        try {
            materialService.upload(material, file);
            model.addAttribute("message", "파일 업로드가 완료되었습니다.");
        } catch (Exception e) {
            model.addAttribute("message", "파일 업로드 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return "redirect:/material/list_material"; // 업로드 후 리다이렉트할 페이지
    }

}*/
    /*

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
    }*/

    /*@PostMapping("/register")
    public String registerMaterial(
            @ModelAttribute MaterialDTO materialDTO,
            @RequestParam("uploadFiles") MultipartFile[] uploadFiles,
            @RequestParam(name = "box") String box,
            @RequestParam(name = "materS", required=false) String materS,
            Model model) {

        // 자재 정보 저장
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO = memberService.findMemberDto(authMemberDTO.getMno(), null);

        materialDTO.setMemberDTO(memberDTO);
        //String제공받은거 long으로 변환함
        materialDTO.setBoxDTO(BoxDTO.builder().boxcode(Long.valueOf(box)).build());
        materialDTO.setMaterSDTO(materService.findMaterS(Long.valueOf(materS)));

        materialService.insertj(materialDTO);

        // 파일 저장 및 메타데이터 저장
        File directory = new File(UPLOADED_FOLDER);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        for (MultipartFile file : uploadFiles) {
            if (!file.isEmpty()) {
                try {
                    String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                    Path path = Paths.get(UPLOADED_FOLDER + fileName);
                    Files.write(path, file.getBytes());

                    // 파일 메타데이터 저장
                    FileMetadata metadata = new FileMetadata();
                    metadata.setFileName(fileName);
                    metadata.setFilePath(path.toString());
                    fileMetadataRepository.save(metadata);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        model.addAttribute("message", "파일 업로드와 자재 등록이 완료되었습니다.");
        return "redirect:/material/list_material";
    }*/
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



        // DTO에 파일 이름 설정
        /*materialDTO.setFile(fileName); // 단일 파일이므로 파일 이름을 직접 설정

        // DTO를 엔티티로 변환 후 저장
        Material material = Material.builder()
                .name(materialDTO.getName())
                .unit(materialDTO.getUnit())
                .standard(materialDTO.getStandard())
                .color(materialDTO.getColor())
                .file(fileName) // 단일 파일 이
                .writer(memberService.memberdtoToEntity(memberDTO)
                //.materS(materS)
                //.box(boxService.findBox(materialDTO.getBoxDTO().getBoxcode()))
                .build();

        materialService.insertj(material);

        model.addAttribute("message", "파일 업로드와 자재 등록이 완료되었습니다.");
        return "redirect:/material/list_material";
    }*/




