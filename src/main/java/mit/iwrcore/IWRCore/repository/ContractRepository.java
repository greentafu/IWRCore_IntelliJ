package mit.iwrcore.IWRCore.repository;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("select c, p, pp from Contract c " +
            "join Product p on (c.jodalPlan.proPlan.product.manuCode=p.manuCode) " +
            "join ProPlan pp on(c.jodalPlan.proPlan.proplanNo=pp.proplanNo) " +
            "where c.conNo=:conNo")
    List<Object[]> findContract(Long conNo);

    @Query("select j, c, jc from JodalPlan j " +
            "left join Contract c on (j.joNo=c.jodalPlan.joNo) " +
            "left join JodalChasu jc on (j.joNo=jc.jodalPlan.joNo) " +
            "where jc.jcnum = (select min(jc2.jcnum) from JodalChasu jc2 where jc2.jodalPlan.joNo = j.joNo)")
    Page<Object[]> yesplanMaterial(Pageable pageable);

    @Query("select j, c, jc, p from JodalPlan j " +
            "left join Contract c on (j.joNo=c.jodalPlan.joNo) " +
            "left join JodalChasu jc on (j.joNo=jc.jodalPlan.joNo) " +
            "left join Product p on (c.jodalPlan.proPlan.product.manuCode=p.manuCode) " +
            "where jc.jcnum = (select min(jc2.jcnum) from JodalChasu jc2 where jc2.jodalPlan.joNo = j.joNo) " +
            "and j.joNo not in (select c2.jodalPlan.joNo from Contract c2)")
    Page<Object[]> couldContractMaterial(Pageable pageable);

    @Query("select c, p, pp from Contract c " +
            "left join Product p on (c.jodalPlan.proPlan.product.manuCode=p.manuCode) " +
            "left join ProPlan pp on (c.jodalPlan.proPlan.proplanNo=pp.proplanNo) " +
            "where c.partner.pno=:pno")
    Page<Object[]> partnerContractList(Pageable pageable, Long pno);

    @Query("select c, jc from Contract c " +
            "left join Balju b on (c.conNo=b.contract.conNo) " +
            "left join JodalChasu jc on (c.jodalPlan.joNo=jc.jodalPlan.joNo) " +
            "where b.baljuNo is null and c.partner.pno=:pno")
    List<Object[]> newOrderContract(Long pno);
}
