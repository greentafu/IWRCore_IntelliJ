package mit.iwrcore.IWRCore.repository;


import mit.iwrcore.IWRCore.entity.GumsuChasu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GumsuChasuRepository extends JpaRepository<GumsuChasu,Long> {
    @Query("select b, c, g, e, r from Balju b " +
            "left join JodalChasu c on (b.contract.jodalPlan.joNo=c.jodalPlan.joNo) " +
            "left join GumsuChasu g on (b.baljuNo=g.gumsu.balju.baljuNo) " +
            "left join Emergency e on (b.baljuNo=e.balju.baljuNo) " +
            "left join Returns r on (b.baljuNo=r.shipment.balju.baljuNo)" +
            "where b.contract.partner.pno=:pno")
    List<Object[]> partnerMain(Long pno);
}
