package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Invoice;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.Shipment;
import mit.iwrcore.IWRCore.repository.InvoiceRepository;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.security.dto.InvoiceDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService{
    private final InvoiceRepository invoiceRepository;
    private final MemberService memberService;

    @Override
    public Invoice convertToEntity(InvoiceDTO dto) {
        return Invoice.builder()
                .tranNO(dto.getTranNO())
                .plz(dto.getPlz())
                .dateCreated(dto.getDateCreated())
                .filename(dto.getFilename())
                .text(dto.getText())
                .cash(dto.getCash())
                .cheque(dto.getCheque())
                .promissory(dto.getPromissory())
                .receivable(dto.getReceivable())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO())) // MemberDTO를 Member로 변환
                .build();
    }

    @Override
    public InvoiceDTO convertToDTO(Invoice entity) {
        return InvoiceDTO.builder()
                .tranNO(entity.getTranNO())
                .plz(entity.getPlz())
                .dateCreated(entity.getDateCreated())
                .filename(entity.getFilename())
                .text(entity.getText())
                .cash(entity.getCash())
                .cheque(entity.getCheque())
                .promissory(entity.getPromissory())
                .receivable(entity.getReceivable())
                .memberDTO(memberService.memberTodto(entity.getWriter())) // Member를 MemberDTO로 변환
                .build();
    }

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = convertToEntity(invoiceDTO);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return convertToDTO(savedInvoice);
    }

    @Override
    public InvoiceDTO getInvoiceById(Long id) {
        return convertToDTO(invoiceRepository.findById(id).get());
    }

    @Override
    public InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO) {
        if (!invoiceRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 InvoiceDTO를 찾을 수 없습니다.");
        }
        Invoice invoice = convertToEntity(invoiceDTO);
        invoice.setTranNO(id); // 수정할 때 ID를 설정합니다.
        Invoice updatedInvoice = invoiceRepository.save(invoice);
        return convertToDTO(updatedInvoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 InvoiceDTO를 찾을 수 없습니다.");
        }
        invoiceRepository.deleteById(id);
    }

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}