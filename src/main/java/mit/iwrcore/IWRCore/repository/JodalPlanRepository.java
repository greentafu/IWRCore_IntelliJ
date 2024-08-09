package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.JodalPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JodalPlanRepository extends JpaRepository<JodalPlan, Long> {
    List<JodalPlan> findByProPlanId(Long proPlanId);
}
