package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Partner;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Partner p where p.id =:id")
    Optional<Partner> findByID(String id);
}
