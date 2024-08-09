package mit.iwrcore.IWRCore.repository;


import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    @Query("select m from Material m")
    List<Material> materialList();
    @Query("select m from Material m where m.box.boxCode=:boxcode or m.materS.materScode=:materscode")
    List<Material> materialListPart(Long boxcode, Long materscode);
}
