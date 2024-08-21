package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Gumsu;
import mit.iwrcore.IWRCore.entity.Partner;
import mit.iwrcore.IWRCore.security.dto.multiDTO.BaljuGumsuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GumsuReposetory extends JpaRepository<Gumsu,Long> {
    @Query("select b, g, b.contract from Balju b " +
            "left join Gumsu g on (b.baljuNo=g.balju.baljuNo) " +
            "where b.baljuNo not in (select g2.balju.baljuNo from Gumsu g2)")
    Page<Object[]> couldGumsu(Pageable pageable);

    @Query("select g.make from Gumsu g where g.balju.baljuNo=:baljuNo")
    Long quantityMake(Long baljuNo);

    @Query("select g, g.balju.contract from Gumsu g where g.balju.baljuNo=:baljuNo")
    List<Object[]> getGumsuFromBalju(Long baljuNo);

    @Query("select distinct b.contract.partner from Balju b " +
            "left join Gumsu g on (b.baljuNo=g.balju.baljuNo) " +
            "where b.baljuNo not in (select g2.balju.baljuNo from Gumsu g2)")
    List<Partner> getNonGumsuPartner();

    @Query("select b, jc, b.contract from Balju b " +
            "left join Gumsu g on(g.balju.baljuNo=b.baljuNo) " +
            "left join JodalChasu jc on (b.contract.jodalPlan.joNo=jc.jodalPlan.joNo) " +
            "where b.baljuNo not in (select g2.balju.baljuNo from Gumsu g2) and b.contract.partner.pno=:pno")
    List<Object[]> getNoneGumsu(Long pno);
}
