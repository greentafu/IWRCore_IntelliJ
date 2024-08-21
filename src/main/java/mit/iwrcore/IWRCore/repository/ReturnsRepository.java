package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.Returns;
import mit.iwrcore.IWRCore.entity.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReturnsRepository extends JpaRepository<Returns,Long> {
    @Transactional
    @EntityGraph(attributePaths = {"shipment", "writer"})
    @Query("select r from Returns r where r.shipment.balju.baljuNo=:baljuNo")
    List<Returns> getReturns(Long baljuNo);

    @Modifying
    @Transactional
    @EntityGraph(attributePaths = {"shipment", "writer"})
    @Query("update Returns r set r.shipment=:shipment where r.reNO=:reNO")
    void updateReturnsShipment(Shipment shipment, Long reNO);

    @Modifying
    @Transactional
    @EntityGraph(attributePaths = {"shipment", "writer"})
    @Query("update Returns r set r.returnCheck=1 where r.reNO=:reNO")
    void updateReturnsCheck(Long reNO);

    @Transactional
    @EntityGraph(attributePaths = {"shipment", "writer"})
    @Query("select r, s.shipNum, s.regDate, b, r.shipment.balju.contract from Returns r " +
            "left join Shipment s on (r.shipment.shipNO=s.shipNO) " +
            "left join Balju b on (r.shipment.balju.baljuNo=b.baljuNo)" +
            "where r.shipment.balju.contract.partner.pno=:pno")
    Page<Object[]> pageReturns(Pageable pageable, Long pno);

    @Transactional
    @EntityGraph(attributePaths = {"shipment", "writer"})
    @Query("select r, s.shipNum, s.regDate, b, r.shipment.balju.contract from Returns r " +
            "left join Shipment s on (r.shipment.shipNO=s.shipNO) " +
            "left join Balju b on (r.shipment.balju.baljuNo=b.baljuNo)" +
            "where r.reNO=:reNO")
    List<Object[]> detailReturns(Long reNO);
}
