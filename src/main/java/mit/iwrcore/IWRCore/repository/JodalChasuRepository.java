package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.JodalChasu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JodalChasuRepository extends JpaRepository<JodalChasu, Long> {
    @Query("select j from JodalChasu j where j.jodalPlan.joNo=:jpnum")
    List<JodalChasu> getJodalChausFromPlan(Long jpnum);
}
