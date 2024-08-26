package mit.iwrcore.IWRCore.repository;


import mit.iwrcore.IWRCore.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByProduct_ManuCode(Long manuCode);

    @Query("select p, pr from Plan p join Product pr on(p.product.manuCode=pr.manuCode)")
    List<Object[]> findPlan(Long manuCode);


}
