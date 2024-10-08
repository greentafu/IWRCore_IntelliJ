package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
    @Query("select s from Shipment s where s.balju.baljuNo=:baljuNo")
    List<Shipment> getShipmentByBalju(Long baljuNo);

    @EntityGraph(attributePaths = {"balju", "returns", "invoice"})
    @Query("select s, g, sss.totalsum, r.reNO, s.balju.contract, p, pp from Shipment s " +
            "left join Gumsu g on (s.balju.baljuNo=g.balju.baljuNo) " +
            "left join (select ss.balju.baljuNo as baljuNo1, sum(ss.shipNum) as totalsum " +
                    "from Shipment ss " +
                    "where ss.receiveCheck=1 " +
                    "group by ss.balju.baljuNo) as sss " +
            "on (s.balju.baljuNo=sss.baljuNo1) " +
            "left join Returns r on (s.shipNO=r.shipment.shipNO) " +
            "left join Product p on (p.manuCode=s.balju.contract.jodalPlan.proPlan.product.manuCode) " +
            "left join ProPlan pp on (pp.proplanNo=s.balju.contract.jodalPlan.proPlan.proplanNo)")
    Page<Object[]> shipmentPage(Pageable pageable);

    @EntityGraph(attributePaths = {"balju", "returns", "invoice"})
    @Query("select s, g, sss.totalsum, r.reNO, s.balju.contract, p, pp from Shipment s " +
            "left join Gumsu g on (s.balju.baljuNo=g.balju.baljuNo) " +
            "left join (select ss.balju.baljuNo as baljuNo1, sum(ss.shipNum) as totalsum " +
            "from Shipment ss " +
            "where ss.receiveCheck=1 " +
            "group by ss.balju.baljuNo) as sss " +
            "on (s.balju.baljuNo=sss.baljuNo1) " +
            "left join Returns r on (s.shipNO=r.shipment.shipNO) " +
            "left join Product p on (p.manuCode=s.balju.contract.jodalPlan.proPlan.product.manuCode) " +
            "left join ProPlan pp on (pp.proplanNo=s.balju.contract.jodalPlan.proPlan.proplanNo) " +
            "where s.receipt is null")
    Page<Object[]> mainShipment(Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Shipment s set s.receipt=:receiveDate where s.shipNO=:shipNo")
    void updateShipmentDate(LocalDateTime receiveDate, Long shipNo);

    @Modifying
    @Transactional
    @EntityGraph(attributePaths = {"balju"})
    @Query("update Shipment s set s.writer=:member, s.receiveCheck=1 where s.shipNO=:shipNo")
    void updateShipmentMemberCheck(Member member, Long shipNo);

    @Modifying
    @Transactional
    @EntityGraph(attributePaths = {"balju", "writer", "returns", "invoice"})
    @Query("update Shipment s set s.returns=:returns where s.shipNO=:shipNo")
    void updateShipmentReturns(Returns returns, Long shipNo);

    @Modifying
    @Transactional
    @EntityGraph(attributePaths = {"balju", "writer", "returns", "invoice"})
    @Query("update Shipment s set s.invoice=null, s.bGo=null where s.shipNO=:shipNo")
    void updateShipmentInvoiceText(Long shipNo);

    @Modifying
    @Transactional
    @EntityGraph(attributePaths = {"balju", "writer", "returns", "invoice"})
    @Query("update Shipment s set s.invoice=:invoice, s.bGo=:text where s.shipNO=:shipNo")
    void updateShipmentInvoice(Invoice invoice, String text, Long shipNo);

    @Transactional
    @EntityGraph(attributePaths = {"balju", "writer", "returns", "invoice"})
    @Query("select s from Shipment s where s.shipNO=:shipNo")
    Shipment findShipment(Long shipNo);

    @Transactional
    @EntityGraph(attributePaths = {"balju", "writer", "returns", "invoice"})
    @Query("select s, s.balju.contract, p, pp from Shipment s " +
            "left join Product p on (p.manuCode=s.balju.contract.jodalPlan.proPlan.product.manuCode) " +
            "left join ProPlan pp on (pp.proplanNo=s.balju.contract.jodalPlan.proPlan.proplanNo) " +
            "where s.receiveCheck=1 and s.invoice is null")
    Page<Object[]> noneInvoiceShipment(Pageable pageable);

    @Transactional
    @EntityGraph(attributePaths = {"balju", "writer", "returns", "invoice"})
    @Query("select s, s.balju.contract, p, pp from Shipment s " +
            "left join Product p on (p.manuCode=s.balju.contract.jodalPlan.proPlan.product.manuCode) " +
            "left join ProPlan pp on (pp.proplanNo=s.balju.contract.jodalPlan.proPlan.proplanNo) " +
            "where s.receiveCheck=1 and s.invoice is null and s.balju.contract.partner.pno=:pno")
    List<Object[]> couldInvoice(Long pno);

    @Transactional
    @EntityGraph(attributePaths = {"balju", "writer", "returns", "invoice"})
    @Query("select distinct s.balju.contract.partner from Shipment s " +
            "where s.receiveCheck=1 and s.invoice is null")
    List<Partner> couldInvoicePartner();

    @Transactional
    @EntityGraph(attributePaths = {"balju", "writer", "returns", "invoice"})
    @Query("select distinct i, s.balju.contract.partner, p, pp from Invoice i " +
            "join Shipment s on (s.invoice.tranNO=i.tranNO) " +
            "left join Product p on (p.manuCode=s.balju.contract.jodalPlan.proPlan.product.manuCode) " +
            "left join ProPlan pp on (pp.proplanNo=s.balju.contract.jodalPlan.proPlan.proplanNo) " +
            "where s.invoice is not null")
    Page<Object[]> finInvoicePage(Pageable pageable);

    @Transactional
    @EntityGraph(attributePaths = {"balju", "writer", "returns", "invoice"})
    @Query("select distinct i, s.balju.contract.partner, p, pp from Invoice i " +
            "join Shipment s on (s.invoice.tranNO=i.tranNO) " +
            "left join Product p on (p.manuCode=s.balju.contract.jodalPlan.proPlan.product.manuCode) " +
            "left join ProPlan pp on (pp.proplanNo=s.balju.contract.jodalPlan.proPlan.proplanNo) " +
            "where s.invoice is not null and s.balju.contract.partner.pno=:pno")
    Page<Object[]> partnerInvoicePage(Pageable pageable, Long pno);

    @Query("select sum(s.shipNum) from Shipment s where s.balju.contract.jodalPlan.joNo=:joNo")
    Long allShipNum(Long joNo);

    @Query("SELECT s FROM Shipment s " +
            "JOIN FETCH s.balju b " +
            "JOIN FETCH b.contract c " +
            "JOIN FETCH c.jodalPlan jp " +
            "JOIN FETCH jp.material m " +
            "WHERE s.receiveCheck = :receiveCheck")
    List<Shipment> findByReceiveCheckWithDetails(Long receiveCheck);

    @Query("select s from Shipment s where s.invoice.tranNO=:tranNO")
    List<Shipment> getInvoiceContent(Long tranNO);

    @Transactional
    @EntityGraph(attributePaths = {"balju", "writer", "returns", "invoice"})
    @Query("select count(s) from Shipment s " +
            "left join Returns r on (s.shipNO=r.shipment.shipNO) " +
            "where s.receiveCheck=0 and r is null")
    Long mainShipment();
}
