package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Balju;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractBaljuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BaljuRepository extends JpaRepository<Balju, Long> {


    @EntityGraph(attributePaths = {"contract"})
    @Query("select b.contract, b, p, pro from Balju b " +
            "left join Product p on (b.contract.jodalPlan.proPlan.product.manuCode=p.manuCode) " +
            "left join ProPlan pro on (pro.proplanNo=b.contract.jodalPlan.proPlan.proplanNo)")
    Page<Object[]> finBaljuPage(Pageable pageable);




    @Query("select b from Balju b")
    Page<Balju> finishBalju(Pageable pageable);

    @EntityGraph(attributePaths = {"contract"})
    @Query("select c, b, p, pro from Contract c " +
            "left join Balju b on (c.conNo=b.contract.conNo) " +
            "left join Product p on (c.jodalPlan.proPlan.product.manuCode=p.manuCode) "+
            "left join ProPlan pro on (pro.proplanNo=c.jodalPlan.proPlan.proplanNo)")
    Page<Object[]> finishContract(Pageable pageable);

    @Query("select c, b from Contract c " +
            "left join Balju b on (c.conNo=b.contract.conNo) " +
            "where c.conNo not in (select b2.contract.conNo from Balju b2)")
    Page<Object[]> couldBalju(Pageable pageable);

    @Query("select b, b.contract from Balju b where b.contract.partner.pno=:pno")
    Page<Object[]> partnerBaljuList(Pageable pageable, Long pno);




    @EntityGraph(attributePaths = {"contract"})
    @Query("select b, b.contract, p, pro from Balju b " +
            "left join Product p on (b.contract.jodalPlan.proPlan.product.manuCode=p.manuCode) " +
            "left join ProPlan pro on (pro.proplanNo=b.contract.jodalPlan.proPlan.proplanNo) " +
            "where b.contract.partner.pno=:pno")
    List<Object[]> partListBalju(Long pno);



    @EntityGraph(attributePaths = {"contract"})
    @Query("select b, b.contract, p, pro from Balju b " +
            "left join Product p on (b.contract.jodalPlan.proPlan.product.manuCode=p.manuCode) " +
            "left join ProPlan pro on (pro.proplanNo=b.contract.jodalPlan.proPlan.proplanNo) " +
            "where b.baljuNo=:baljuNo")
    List<Object[]> findBaljuFromNo(Long baljuNo);
}
