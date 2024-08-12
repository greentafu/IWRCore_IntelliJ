package mit.iwrcore.IWRCore.repository;


import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long>, QuerydslPredicateExecutor<Material> {
    @Query(value = "select m from Material m", countQuery = "select count(m) from Material m where m.materCode>:materCode")
    List<Material> materialList(Pageable pageable);
    @Query("select m from Material m where m.box.boxCode=:boxcode or m.materS.materScode=:materscode")
    List<Material> materialListPart(Long boxcode, Long materscode);
}
