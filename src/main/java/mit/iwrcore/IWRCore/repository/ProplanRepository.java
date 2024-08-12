package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.ProPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProplanRepository extends JpaRepository<ProPlan, Long> {

    @Query(value = "select p from ProPlan p", countQuery = "select count(p) from ProPlan p where p.proplanNo>:proplanNo")
    List<ProPlan> proPlanList(Pageable pageable);

    List<ProPlan> findByProplanNo(Long proplanNo);
}
