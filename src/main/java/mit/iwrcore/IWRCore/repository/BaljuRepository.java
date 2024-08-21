package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Balju;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractBaljuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BaljuRepository extends JpaRepository<Balju, Long> {
    @Query("select b.contract, b from Balju b")
    Page<Object[]> finBaljuPage(Pageable pageable);

    @Query("select b from Balju b")
    Page<Balju> finishBalju(Pageable pageable);

    @Query("select c, b from Contract c " +
            "left join Balju b on (c.conNo=b.contract.conNo)")
    Page<Object[]> finishContract(Pageable pageable);

    @Query("select c, b from Contract c " +
            "left join Balju b on (c.conNo=b.contract.conNo) " +
            "where c.conNo not in (select b2.contract.conNo from Balju b2)")
    Page<Object[]> couldBalju(Pageable pageable);

    @Query("select b, b.contract from Balju b where b.contract.partner.pno=:pno")
    Page<Object[]> partnerBaljuList(Pageable pageable, Long pno);

    @Query("select b, b.contract from Balju b where b.contract.partner.pno=:pno")
    List<Object[]> partListBalju(Long pno);

    @Query("select b, b.contract from Balju b where b.baljuNo=:baljuNo")
    List<Object[]> findBaljuFromNo(Long baljuNo);
}
