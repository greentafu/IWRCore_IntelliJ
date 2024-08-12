package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Invoice;
import mit.iwrcore.IWRCore.security.dto.InvoiceDTO;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    // DTO를 엔티티로 변환
    Invoice convertToEntity(InvoiceDTO dto);

    // 엔티티를 DTO로 변환
    InvoiceDTO convertToDTO(Invoice entity);

    // 기타 CRUD 메서드
    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);

    Optional<InvoiceDTO> getInvoiceById(Long id);

    InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO);

    void deleteInvoice(Long id);

    List<InvoiceDTO> getAllInvoices();
}