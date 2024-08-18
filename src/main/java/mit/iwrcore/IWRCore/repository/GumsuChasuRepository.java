package mit.iwrcore.IWRCore.repository;


import mit.iwrcore.IWRCore.entity.GumsuChasu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GumsuChasuRepository extends JpaRepository<GumsuChasu,Long> {
    @Query("select g from GumsuChasu g where g.gumsu.balju.baljuNo=:baljuNo")
    List<GumsuChasu> getGumsuChasuByBaljuNo(Long baljuNo);
}
