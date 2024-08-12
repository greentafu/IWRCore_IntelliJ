package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice , Long> {
}
