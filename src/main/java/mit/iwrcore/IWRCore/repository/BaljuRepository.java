package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Balju;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractBaljuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BaljuRepository extends JpaRepository<Balju, Long> {
    @Query("select c, b from Contract c left join Balju b on (c.conNo=b.contract.conNo)")
    Page<Object[]> finishContract(Pageable pageable);
}
