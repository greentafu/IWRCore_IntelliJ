package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Gumsu;
import mit.iwrcore.IWRCore.security.dto.multiDTO.BaljuGumsuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GumsuReposetory extends JpaRepository<Gumsu,Long> {
    @Query("select b, g from Balju b left join Gumsu g on (b.baljuNo=g.balju.baljuNo) where b.baljuNo not in (select g2.balju.baljuNo from Gumsu g2)")
    Page<Object[]> couldGumsu(Pageable pageable);

    @Query("select g.make from Gumsu g where g.balju.baljuNo=:baljuNo")
    Long quantityMake(Long baljuNo);
}
