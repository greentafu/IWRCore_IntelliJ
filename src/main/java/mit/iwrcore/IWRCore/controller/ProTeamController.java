package mit.iwrcore.IWRCore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.RequestRepository;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.AuthDTO.AuthMemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
;

@Controller
@RequestMapping("/proteam")
@RequiredArgsConstructor
@Log4j2
public class ProTeamController {

    private final ProductService productService;
    private final ProplanService proplanService;
    private final RequestService requestService;
    private final PlanService planService;
    private final MemberService memberService;
    private final JodalPlanService jodalPlanService;
    private final MaterialService materialService;
    private final StructureService structureService;
    private final RequestRepository requestRepository;
    private final ShipmentService shipmentService;


    @GetMapping("/list_pro")
    public void list_pro(PageRequestDTO pageRequestDTO, PageRequestDTO2 pageRequestDTO2, Model model) {
        model.addAttribute("product_list", productService.getNonPlanProducts(pageRequestDTO));
        model.addAttribute("proplan_list", proplanService.proplanList2(pageRequestDTO2));
    }

    @GetMapping("/list_request")
    public void list_request(PageRequestDTO requestDTO, Model model) {
        model.addAttribute("list", requestService.requestPage(requestDTO));
    }

    @GetMapping("/input_pro")
    public void input_pro(@RequestParam("manuCode") Long manuCode, Model model) {

        model.addAttribute("plan_list", planService.findByProductId(manuCode));

        // 제품 코드를 이용해 제품 정보를 가져옵니다.
        ProductDTO product = productService.getProductById(manuCode);

        // 가져온 제품 정보를 모델에 추가하여 뷰로 전달합니다.
        model.addAttribute("product", product);
    }

    @PostMapping("/save_plan")
    public String savePlan(
            @RequestParam("manuCode") Long manuCode,
            @RequestParam(name = "lineA", required = false) Long lineA,
            @RequestParam(name = "lineB", required = false) Long lineB,
            @RequestParam(name = "lineC", required = false) Long lineC,
            @RequestParam(name = "quantities", required = false) List<Long> quantities,
            @RequestParam(name = "lines", required = false) List<String> lines) {

        // 제품 정보 가져오기
        ProductDTO product = productService.getProductById(manuCode);

        // 기존 계획 업데이트
        List<PlanDTO> existingPlans = planService.findByProductId(manuCode);
        if (!existingPlans.isEmpty()) {
            for (int i = 0; i < existingPlans.size(); i++) {
                PlanDTO plan = existingPlans.get(i);
                Long quantity = quantities != null && i < quantities.size() ? quantities.get(i) : null;
                if (quantity != null) {
                    plan.setQuantity(quantity);
                    planService.update(plan);
                }
            }
        }

        // 새 계획 저장
        if (lineA != null) {
            PlanDTO newPlan = PlanDTO.builder()
                    .line("A")
                    .quantity(lineA)
                    .productDTO(product)
                    .build();
            planService.save(newPlan);
        }
        if (lineB != null) {
            PlanDTO newPlan = PlanDTO.builder()
                    .line("B")
                    .quantity(lineB)
                    .productDTO(product)
                    .build();
            planService.save(newPlan);
        }
        if (lineC != null) {
            PlanDTO newPlan = PlanDTO.builder()
                    .line("C")
                    .quantity(lineC)
                    .productDTO(product)
                    .build();
            planService.save(newPlan);
        }

        // 리디렉션
        return "redirect:/proteam/input_pro?manuCode=" + manuCode;
    }


    @PostMapping("/Psave")
    public String PlanSave(
            @RequestParam(required = false) Long proplanNo,
            @RequestParam("manuCode") Long manuCode,
            @RequestParam("line") List<String> lines,
            @RequestParam("pronum") Long pronum,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam("details") String details) {

        // 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO = memberService.findMemberDto(authMemberDTO.getMno(), null);

        // DTO 객체 생성 및 설정
        ProplanDTO dto = ProplanDTO.builder()
                .proplanNo((proplanNo!=null)?proplanNo:null)
                .productDTO(productService.getProductById(manuCode))
                .pronum(pronum)
                .startDate(startDate.atStartOfDay()) // LocalDate를 LocalDateTime으로 변환
                .endDate(endDate.atStartOfDay())     // LocalDate를 LocalDateTime으로 변환
                .line(String.join(", ", lines)) // 라인 정보를 콤마로 구분하여 설정
                .details(details)
                .memberDTO(memberDTO) // memberDTO 설정
                .build();

        // DTO를 서비스에 저장
        ProplanDTO proplanDTO = proplanService.save(dto);

        // JodalPlanService 호출
        if(proplanNo==null) jodalPlanService.saveFromProplan(proplanDTO, memberDTO);

        // 리디렉션
        return "redirect:/proteam/list_pro";
    }

    @GetMapping("/input_request")
    public void input_request() {

    }

    @GetMapping("/details_plan")
    public void details_plan() {

    }

    @GetMapping("/details_request")
    public void details_request() {

    }

    @GetMapping("/modify_plan")
    public void modify_plan(Long proplanNo, Model model) {
        ProplanDTO proplanDTO=proplanService.findById(proplanNo);
        Long manuCode=proplanDTO.getProductDTO().getManuCode();
        model.addAttribute("plan_list", planService.findByProductId(manuCode));
        model.addAttribute("product", proplanDTO.getProductDTO());
        model.addAttribute("proplan", proplanDTO);
    }

    @GetMapping("/modify_request")
    public void modify_request() {

    }

    @GetMapping("/delete_proplan")
    public String delete_proplan(@RequestParam(required = false) Long proplanNo) {
        List<JodalPlanDTO> jodalPlanDTOs=jodalPlanService.findJodalPlanByProPlan(proplanNo);
        if(jodalPlanDTOs!=null){
            jodalPlanDTOs.forEach(x->jodalPlanService.deleteById(x.getJoNo()));
        }
        proplanService.deleteById(proplanNo);
        return "redirect:/proteam/list_pro";
    }

    @PostMapping("/save_request")
    public void save_request() {

    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam(value = "query", defaultValue = "") String query) {
        List<ProductDTO> products;

        try {
            if (query.isEmpty() || "all".equals(query)) {
                // 모든 제품을 가져오는 메서드에서 PageResultDTO<ProductDTO, Product>를 가져옴
                PageResultDTO<ProductDTO, Product> pageResult = productService.getAllProducts(new PageRequestDTO());

                // ProductDTO 리스트를 직접 가져옴 (ProPlans는 이미 getAllProducts에서 설정됨)
                products = pageResult.getDtoList();
            } else {
                // 검색 결과를 가져오는 메서드에서 결과를 List<ProductDTO>로 변환
                products = productService.searchProducts(query);
            }

            if (products.isEmpty()) {
                return ResponseEntity.noContent().build(); // 데이터가 없는 경우
            }

            return ResponseEntity.ok(products);
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/material-structure")
    public ResponseEntity<Map<String, Object>> getMaterialStructure(@RequestParam("manuCode") Long manuCode) {
        try {
            // 제품 정보 조회
            ProductDTO product = productService.getProductById(manuCode);
            if (product == null) {
                return ResponseEntity.notFound().build(); // 제품이 없는 경우
            }

            // 구조 정보 조회
            List<StructureDTO> structures = structureService.findByProduct_ManuCode(manuCode);
            if (structures.isEmpty()) {
                return ResponseEntity.noContent().build(); // 구조 정보가 없는 경우
            }

            // 자재 정보 조회
            List<MaterialDTO> materials = structures.stream()
                    .map(StructureDTO::getMaterialDTO) // 자재 DTO 추출
                    .distinct() // 중복 제거
                    .collect(Collectors.toList());

            // 자재별 재고 계산을 위한 Map 생성 (materialCode -> stock)
            Map<Long, Long> materialStockMap = new HashMap<>();

            // 1. receiveCheck가 1인 모든 Shipment 가져오기
            List<ShipmentDTO> shipments = shipmentService.getShipmentsByReceiveCheck(1);
            log.info("Received Shipments with receiveCheck 1: {}", shipments);

            // 2. reqCheck가 1인 모든 Request 가져오기
            List<RequestDTO> requests = requestService.getRequestsByReqCheck(1);
            log.info("Received Requests with reqCheck 1: {}", requests);

            // Shipment 데이터를 통해 초기 재고 설정
            for (ShipmentDTO shipment : shipments) {
                Long materialCode = shipment.getBaljuDTO().getContractDTO().getJodalPlanDTO().getMaterialDTO().getMaterCode();
                log.info("Processing Shipment: Material Code: {}, Ship Num: {}", materialCode, shipment.getShipNum());
                materialStockMap.put(materialCode, materialStockMap.getOrDefault(materialCode, 0L) + shipment.getShipNum());
            }

            // Request 데이터를 통해 재고 감소
            for (RequestDTO request : requests) {
                Long materialCode = request.getMaterialDTO().getMaterCode();
                log.info("Processing Request: Material Code: {}, Request Num: {}", materialCode, request.getRequestNum());
                materialStockMap.put(materialCode, materialStockMap.getOrDefault(materialCode, 0L) - request.getRequestNum());
            }

            log.info("Final Material Stock Map: {}", materialStockMap);

            // 응답 데이터 생성
            Map<String, Object> response = new HashMap<>();
            response.put("product", product);
            response.put("materials", materials); // MaterialDTO 목록을 직접 사용
            response.put("structures", structures);
            response.put("materialStocks", materialStockMap); // 자재별 재고 정보 추가

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in getMaterialStructure: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/submitRequest")
    public ResponseEntity<Void> submitRequest(@RequestBody List<RequestDTO> requestDTOs) {
        log.info("Received RequestDTOs: {}", requestDTOs);

        // 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
        MemberDTO memberDTO = memberService.findMemberDto(authMemberDTO.getMno(), null);
        Member writer = memberService.memberdtoToEntity(memberDTO);

        try {
            for (RequestDTO requestDTO : requestDTOs) {
                // Request 엔티티 생성 및 필수 정보 설정
                Request request = new Request();
                request.setWriter(writer);
                request.setRequestNum(requestDTO.getRequestNum());
                request.setEventDate(requestDTO.getEventDate());
                request.setText(requestDTO.getText());
                request.setReqCheck(requestDTO.getReqCheck());
                request.setLine(requestDTO.getLine());

                // Material 변환 및 설정 - materCode만 사용
                if (requestDTO.getMaterialDTO() != null && requestDTO.getMaterialDTO().getMaterCode() != null) {
                    // MaterialDTO를 Material로 변환
                    MaterialDTO materialDTO = materialService.findM(requestDTO.getMaterialDTO().getMaterCode());
                    Material material = materialService.materdtoToEntity(materialDTO); // DTO를 엔티티로 변환
                    if (material != null) {
                        request.setMaterial(material);
                    } else {
                        log.warn("Material conversion failed for MaterialDTO: {}", requestDTO.getMaterialDTO());
                    }
                } else {
                    log.warn("MaterialDTO is null or materCode is missing for requestDTO: {}", requestDTO);
                }

                // ProPlan 변환 및 설정 - proplanNo만 사용
                if (requestDTO.getProplanDTO() != null && requestDTO.getProplanDTO().getProplanNo() != null) {
                    // ProplanDTO를 ProPlan으로 변환
                    ProplanDTO proplanDTO = proplanService.findById(requestDTO.getProplanDTO().getProplanNo());
                    ProPlan proPlan = proplanService.dtoToEntity(proplanDTO); // DTO를 엔티티로 변환
                    if (proPlan != null) {
                        request.setProPlan(proPlan);
                    } else {
                        log.warn("ProPlan conversion failed for ProplanDTO: {}", requestDTO.getProplanDTO());
                    }
                } else {
                    log.warn("ProplanDTO is null or proplanNo is missing for requestDTO: {}", requestDTO);
                }

                // Request 엔티티를 데이터베이스에 저장
                requestRepository.save(request);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error processing RequestDTO: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/login/emergency-delivery-requests")
    public ResponseEntity<List<RequestDTO>> getEmergencyDeliveryRequests(
            @RequestParam(value = "orderBy", required = false) String orderBy) {

        // "부족"이라는 키워드를 포함하는 요청 목록을 가져옵니다.
        List<RequestDTO> requests = requestService.getRequestsByTextContains("부족");

        // 정렬 기준에 따라 리스트를 정렬합니다.
        if ("high".equals(orderBy)) {
            requests = requests.stream()
                    .sorted(Comparator.comparing(RequestDTO::getRequestNum).reversed())
                    .collect(Collectors.toList());
        } else if ("low".equals(orderBy)) {
            requests = requests.stream()
                    .sorted(Comparator.comparing(RequestDTO::getRequestNum))
                    .collect(Collectors.toList());
        } else if ("early".equals(orderBy)) {
            requests = requests.stream()
                    .sorted(Comparator.comparing(RequestDTO::getEventDate))
                    .collect(Collectors.toList());
        } else if ("late".equals(orderBy)) {
            requests = requests.stream()
                    .sorted(Comparator.comparing(RequestDTO::getEventDate).reversed())
                    .collect(Collectors.toList());
        }

        // 정렬된 요청 목록을 JSON 형식으로 반환합니다.
        return ResponseEntity.ok(requests);
    }



}


