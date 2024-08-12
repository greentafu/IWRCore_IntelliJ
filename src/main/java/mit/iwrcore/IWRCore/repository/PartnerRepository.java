package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Partner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long>, QuerydslPredicateExecutor<Partner> {
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Partner p where p.id =:id")
    Optional<Partner> findByID(String id);

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Partner p where p.pno=:pno or p.id =:id or p.registrationNumber=:reg")
    Partner findPartner(Long pno, String id, String reg);

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(value = "select p from Partner p", countQuery = "select count(p) from Partner p where p.pno>:pno")
    List<Partner> findPartnerList(Pageable pageable);
}
