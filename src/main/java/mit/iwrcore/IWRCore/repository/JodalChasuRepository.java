package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.JodalChasu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JodalChasuRepository extends JpaRepository<JodalChasu, Long> {
    List<JodalChasu> findByJodalPlan_JoNo(Long jodalPlanId);
}
