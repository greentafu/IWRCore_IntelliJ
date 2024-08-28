package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.ProPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

public interface ProplanRepository extends JpaRepository<ProPlan, Long> {

    @Query(value = "select p from ProPlan p", countQuery = "select count(p) from ProPlan p where p.proplanNo>:proplanNo")
    List<ProPlan> proPlanList(Pageable pageable);

    List<ProPlan> findByProplanNo(Long proplanNo);

    @Query("select p, pr from ProPlan p join Product pr on (p.product.manuCode=pr.manuCode)")
    Page<Object[]> findproPlanList(Pageable pageable);

    @Query("select p, count(c), count(jc) from ProPlan p " +
            "left join Contract c on (c.jodalPlan.proPlan.proplanNo=p.proplanNo) " +
            "left join JodalChasu jc on (jc.jodalPlan.proPlan.proplanNo=p.proplanNo) " +
            "group by p")
    Page<Object[]> findproPlanList2(Pageable pageable);

    @Query("select count(p) from ProPlan p where p.product.manuCode=:manuCode")
    Long checkProPlan(Long manuCode);


}
