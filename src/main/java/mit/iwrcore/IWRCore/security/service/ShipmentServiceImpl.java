package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.*;
import mit.iwrcore.IWRCore.security.dto.*;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO2;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Log4j2
@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final InvoiceService invoiceService;
    private final BaljuService baljuService;
    private final MemberService memberService;
    private final InvoiceRepository invoiceRepository;
    private final ReturnsRepository returnsRepository;
    private final GumsuService gumsuService;
    private final PartnerService partnerService;
    private final RequestRepository requestRepository;
    @Override
    @Transactional
    public void updateShipmentWithReturns(Long shipmentId, Long returnsId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
        Returns returns = returnsRepository.findById(returnsId)
                .orElseThrow(() -> new RuntimeException("Returns not found"));

        // 출고와 반품 연결 설정
        shipment.setReturns(returns);
        returns.setShipment(shipment);

        shipmentRepository.save(shipment);
        returnsRepository.save(returns);
    }

    @Override
    @Transactional
    public ShipmentDTO createShipmentWithoutInvoice() {
        Shipment shipment = new Shipment();
        // 기본 설정 작업 필요
        Shipment savedShipment = shipmentRepository.save(shipment);
        return convertToDTO(savedShipment);
    }

    @Override
    @Transactional
    public ShipmentDTO linkShipmentToInvoice(Long shipmentId, Long invoiceId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        shipment.setInvoice(invoice);
        Shipment updatedShipment = shipmentRepository.save(shipment);
        return convertToDTO(updatedShipment);
    }

    @Override
    public Shipment convertToEntity(ShipmentDTO dto) {
        return Shipment.builder()
                .shipNO(dto.getShipNO())
                .shipNum(dto.getShipNum())
                .receipt(dto.getReceipt())
                .text(dto.getText())
                .receiveCheck(dto.getReceiveCheck())
                .bGo(dto.getBGo())
                .writer(dto.getMemberDTO()!=null ? memberService.memberdtoToEntity(dto.getMemberDTO()) : null)
                .invoice(dto.getInvoiceDTO() != null ? invoiceService.convertToEntity(dto.getInvoiceDTO()) : null)
                .balju(dto.getBaljuDTO() != null ? baljuService.convertToEntity(dto.getBaljuDTO()) : null)
                .build();
    }

    @Transactional
    @Override
    public ShipmentDTO convertToDTO(Shipment entity) {
        return ShipmentDTO.builder()
                .shipNO(entity.getShipNO())
                .shipNum(entity.getShipNum())
                .receipt(entity.getReceipt())
                .text(entity.getText())
                .receiveCheck(entity.getReceiveCheck())
                .regDate(entity.getRegDate())
                .bGo(entity.getBGo())
                .invoiceDTO(entity.getInvoice() != null ? invoiceService.convertToDTO(entity.getInvoice()) : null)
                .baljuDTO(entity.getBalju() != null ? baljuService.convertToDTO(entity.getBalju()) : null)
                .memberDTO(entity.getWriter()!=null ? memberService.memberTodto(entity.getWriter()) : null)
                .build();
    }


    @Override
    @Transactional
    public ShipmentDTO createShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = convertToEntity(shipmentDTO);
        Shipment savedShipment = shipmentRepository.save(shipment);
        return convertToDTO(savedShipment);
    }

    @Override
    public ShipmentDTO getShipmentById(Long id) {
        return shipmentRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
    }

    @Override
    @Transactional
    public ShipmentDTO updateShipment(Long id, ShipmentDTO shipmentDTO) {
        Shipment existingShipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID가 " + id + "인 ShipmentDTO를 찾을 수 없습니다."));

        if (shipmentDTO.getShipNum() != null) {
            existingShipment.setShipNum(shipmentDTO.getShipNum());
        }
        if (shipmentDTO.getReceipt() != null) {
            existingShipment.setReceipt(shipmentDTO.getReceipt());
        }
        if (shipmentDTO.getInvoiceDTO() != null) {
            existingShipment.setInvoice(invoiceService.convertToEntity(shipmentDTO.getInvoiceDTO()));
        } else {
            existingShipment.setInvoice(null);
        }
        if (shipmentDTO.getBaljuDTO() != null) {
            existingShipment.setBalju(baljuService.convertToEntity(shipmentDTO.getBaljuDTO()));
        } else {
            existingShipment.setBalju(null);
        }
        if (shipmentDTO.getMemberDTO() != null) {
            existingShipment.setWriter(memberService.memberdtoToEntity(shipmentDTO.getMemberDTO()));
        }
        if (shipmentDTO.getReturnsDTO() != null) {
            Returns returns = returnsRepository.findById(shipmentDTO.getReturnsDTO().getReNO())
                    .orElseThrow(() -> new RuntimeException("Returns not found"));
            existingShipment.setReturns(returns);
        } else {
            existingShipment.setReturns(null);
        }

        Shipment updatedShipment = shipmentRepository.save(existingShipment);
        return convertToDTO(updatedShipment);
    }
    @Override
    @Transactional
    public void updateShipmentInvoicebGo(Long shipNo){
        shipmentRepository.updateShipmentInvoiceText(shipNo);
    }

    @Override
    @Transactional
    public void deleteShipment(Long id) {
        if (!shipmentRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 ShipmentDTO를 찾을 수 없습니다.");
        }
        shipmentRepository.deleteById(id);
    }

    @Override
    public List<ShipmentDTO> getAllShipments() {
        return shipmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipmentDTO> getInvoiceContent(Long tranNO){
        return shipmentRepository.getInvoiceContent(tranNO).stream().map(this::convertToDTO).toList();
    }

    @Override
    public ShipmentReturnDTO findShipment(Long shipNo){
        Shipment shipment=shipmentRepository.findShipment(shipNo);
        if(shipment!=null){
            String materialName=shipment.getBalju().getContract().getJodalPlan().getMaterial().getName();
            return new ShipmentReturnDTO(shipNo, shipment.getShipNum(), materialName);
        }else return null;
    }

    @Override
    public void updateShipmentDate(LocalDateTime dateTime, Long shipNo){
        shipmentRepository.updateShipmentDate(dateTime, shipNo);
    }
    @Override
    public void updateMemberCheck(Member member, Long shipNo){
        shipmentRepository.updateShipmentMemberCheck(member, shipNo);
    }
    @Override
    public void updateSHipmentInvoice(Invoice invoice, String text, Long shipNo){
        shipmentRepository.updateShipmentInvoice(invoice, text, shipNo);
    }

    @Override
    public List<ShipmentDTO> getShipmentByBalju(Long baljuNo){
        List<Shipment> entityList=shipmentRepository.getShipmentByBalju(baljuNo);
        List<ShipmentDTO> dtoList=entityList.stream().map(this::convertToDTO).toList();
        return dtoList;
    }
    @Override
    public List<ShipmentDTO> canInvoiceShipment(Long pno){
        List<Object[]> entityList=shipmentRepository.couldInvoice(pno);
        List<ShipmentDTO> dtoList=entityList.stream().map(this::extractShipmentDTO).toList();
        return dtoList;
    }
    private ShipmentDTO extractShipmentDTO(Object[] objects){
        Shipment shipment=(Shipment) objects[0];
        ShipmentDTO shipmentDTO=(shipment!=null)?convertToDTO(shipment):null;
        return shipmentDTO;
    }


    @Override
    public List<PartnerDTO> canInvoicePartner(){
        List<Partner> entityList=shipmentRepository.couldInvoicePartner();
        List<PartnerDTO> dtoList=new ArrayList<>();
        entityList.stream().forEach(x->dtoList.add(partnerService.partnerTodto(x)));
        return dtoList;
    }


    @Override
    public Shipment findShipmentEntity(Long shipNo){
        return shipmentRepository.findShipment(shipNo);
    }

    @Override
    public PageResultDTO<ShipmentGumsuDTO, Object[]> pageShipment(PageRequestDTO requestDTO){
        Pageable pageable=requestDTO.getPageable(Sort.by("shipNO").descending());
        Page<Object[]> entityPage=shipmentRepository.shipmentPage(pageable);
        return new PageResultDTO<>(entityPage, this::shipmentGumsuToDTO);
    }
    private ShipmentGumsuDTO shipmentGumsuToDTO(Object[] objects){
        Shipment shipment=(Shipment) objects[0];
        Gumsu gumsu=(Gumsu) objects[1];
        Long totalShipment=(Long) objects[2];
        Long reNo=(Long) objects[3];
        ShipmentDTO shipmentDTO=(shipment!=null)? convertToDTO(shipment):null;
        GumsuDTO gumsuDTO=(gumsu!=null)? gumsuService.convertToDTO(gumsu):null;
        totalShipment=(totalShipment!=null)?totalShipment:0L;
        reNo=(reNo!=null)?reNo:0L;
        return new ShipmentGumsuDTO(shipmentDTO, gumsuDTO, totalShipment, reNo);
    }
    @Override
    public PageResultDTO<ShipmentGumsuDTO, Object[]> mainShipment(PageRequestDTO requestDTO){
        Pageable pageable=requestDTO.getPageable(Sort.by("shipNO").descending());
        Page<Object[]> entityPage=shipmentRepository.mainShipment(pageable);
        return new PageResultDTO<>(entityPage, this::shipmentGumsuToDTO);
    }

    @Override
    public PageResultDTO<ShipmentDTO, Object[]> noneInvoiceShipment(PageRequestDTO requestDTO){
        Pageable pageable=requestDTO.getPageable(Sort.by("shipNO").descending());
        Page<Object[]> entityPage=shipmentRepository.noneInvoiceShipment(pageable);
        return new PageResultDTO<>(entityPage, this::shipmentContractToDTO);
    }
    private ShipmentDTO shipmentContractToDTO(Object[] objects){
        Shipment shipment=(Shipment) objects[0];
        ShipmentDTO shipmentDTO=(shipment!=null)? convertToDTO(shipment):null;
        return shipmentDTO;
    }

    @Override
    public PageResultDTO<InvoicePartnerDTO, Object[]> pageFinInvoice(PageRequestDTO2 requestDTO2){
        Pageable pageable=requestDTO2.getPageable(Sort.by("tranNO").descending());
        Page<Object[]> entityPage=shipmentRepository.finInvoicePage(pageable);
        return new PageResultDTO<>(entityPage, this::invoicePartnerToDTO);
    }
    private InvoicePartnerDTO invoicePartnerToDTO(Object[] objects){
        Invoice invoice=(Invoice) objects[0];
        Partner partner=(Partner) objects[1];
        InvoiceDTO invoiceDTO=(invoice!=null)? invoiceService.convertToDTO(invoice):null;
        PartnerDTO partnerDTO=(partner!=null)? partnerService.partnerTodto(partner):null;
        return new InvoicePartnerDTO(invoiceDTO, partnerDTO);
    }

    @Override
    public PageResultDTO<InvoicePartnerDTO, Object[]> partnerInvoicePage(PageRequestDTO requestDTO){
        Pageable pageable=requestDTO.getPageable(Sort.by("tranNO").descending());
        Page<Object[]> entityPage=shipmentRepository.partnerInvoicePage(pageable, requestDTO.getPno());
        return new PageResultDTO<>(entityPage, this::invoicePartnerToDTO);
    }

    @Override
    public Long allShipmnetNum(Long joNo){
        return shipmentRepository.allShipNum(joNo);
    }
    @Override
    public Long mainShipNum(){
        Long aa= shipmentRepository.mainShipment();
        return (aa!=null)?aa:0L;
    }



    @Override
    public List<ShipmentDTO> getShipmentsByReceiveCheck(long receiveCheck) {
        List<Shipment> shipments = shipmentRepository.findByReceiveCheckWithDetails(receiveCheck);

        List<ShipmentDTO> shipmentDTOs = shipments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("Converted Shipment Entities to DTOs: {}", shipmentDTOs);
        return shipmentDTOs;
    }

}
