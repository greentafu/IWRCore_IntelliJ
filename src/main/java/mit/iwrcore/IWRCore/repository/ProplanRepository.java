package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.ProPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProplanRepository extends JpaRepository<ProPlan, Long> {
    List<ProPlan> findByPlanId(Long planId);
}
