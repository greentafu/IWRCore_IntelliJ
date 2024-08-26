package mit.iwrcore.IWRCore.repository;


import mit.iwrcore.IWRCore.entity.GumsuChasu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface GumsuChasuRepository extends JpaRepository<GumsuChasu,Long> {
    @Query("select g from GumsuChasu g where g.gumsu.balju.baljuNo=:baljuNo")
    List<GumsuChasu> getGumsuChasuByBaljuNo(Long baljuNo);


    @Query("select gc, gc.gumsu.balju.contract, p, pp from GumsuChasu gc " +
            "left join Product p on (p.manuCode=gc.gumsu.balju.contract.jodalPlan.proPlan.product.manuCode) " +
            "left join ProPlan pp on (pp.proplanNo=gc.gumsu.balju.contract.jodalPlan.proPlan.proplanNo)")
    Page<Object[]> getAllGumsuChasuContract(Pageable pageable);
}
