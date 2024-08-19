package mit.iwrcore.IWRCore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileUpDownLoadController {
    private static final Logger log = LoggerFactory.getLogger(FileUpDownLoadController.class);

    @PostMapping("/uploadAjax")
    public void uploadFile(@RequestParam("uploadFiles") MultipartFile[] uploadFiles) {
        for (MultipartFile uploadFile : uploadFiles) {
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName: " + fileName);
        }
    }
}
/*MaterialController:

@PostMapping("/upload"): 클라이언트에서 파일을 업로드하는 요청을 처리합니다.
RestTemplate: 파일을 업로드하기 위해 REST API 요청을 보냅니다.
HttpHeaders: 요청의 헤더를 설정합니다. 여기서는 MULTIPART_FORM_DATA로 설정합니다.
MultiValueMap: 파일을 포함한 요청 본문을 생성합니다.
HttpEntity: 헤더와 본문을 포함한 요청 엔티티를 생성합니다.
restTemplate.exchange: 업로드 요청을 전송합니다.
FileUpDownLoadController:

@PostMapping("/uploadAjax"): 파일 업로드 요청을 처리합니다.
@RequestParam("uploadFiles"): 업로드된 파일을 MultipartFile[] 배열로 받습니다.
log.info: 파일의 이름을 로깅합니다.
올바르게 요청을 처리하는지 확인하기
서버가 실행 중인지 확인: 두 개의 엔드포인트(/upload 및 /uploadAjax)를 처리할 서버가 실행 중인지 확인합니다.
파일 업로드 폼 테스트: /new_material 페이지에서 파일 업로드 폼을 사용하여 파일을 업로드하고, 업로드가 정상적으로 이루어지는지 확인합니다.
로그 확인: FileUpDownLoadController의 로그에서 파일 이름이 제대로 로깅되는지 확인합니다.
* */
