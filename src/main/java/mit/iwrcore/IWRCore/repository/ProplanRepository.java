package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.ProPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProplanRepository extends JpaRepository<ProPlan, Long> {
    @Query("SELECT pp FROM ProPlan pp WHERE pp.product.plan.id = :planId")
    List<ProPlan> findByPlanId(Long planId);
}
