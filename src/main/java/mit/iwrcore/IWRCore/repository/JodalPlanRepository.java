package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.JodalPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface JodalPlanRepository extends JpaRepository<JodalPlan, Long> {
    List<JodalPlan> findByProPlanProplanNo(Long proplanNo);

    @Query("select j from JodalPlan j where j.joNo not in (select jc.jodalPlan.joNo from JodalChasu jc)")
    Page<JodalPlan> nonPlanMaterial(Pageable pageable);

}
