package mit.iwrcore.IWRCore.repository;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.JodalPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface JodalPlanRepository extends JpaRepository<JodalPlan, Long> {
    List<JodalPlan> findByProPlanProplanNo(Long proplanNo);

    @Query("select j from JodalPlan j where j.joNo not in (select jc.jodalPlan.joNo from JodalChasu jc)")
    Page<JodalPlan> nonPlanMaterial(Pageable pageable);

    @EntityGraph(attributePaths = {"proPlan"})
    @Query("select j, p from JodalPlan j " +
            "join Product p on (j.proPlan.product.manuCode=p.manuCode) " +
            "where j.joNo not in (select jc.jodalPlan.joNo from JodalChasu jc)")
    Page<Object[]> nonPlanMaterial2(Pageable pageable);

    @Query("select j, st, sum(r.requestNum), sum(sh.shipNum) from JodalPlan j " +
            "join Structure st on (j.material.materCode=st.material.materCode and j.proPlan.product.manuCode=st.product.manuCode) " +
            "left join Request r on (j.material.materCode=r.material.materCode and r.reqCheck=1) " +
            "left join Shipment sh on (j.joNo=sh.balju.contract.jodalPlan.joNo and sh.receiveCheck=1) " +
            "where j.joNo not in (select jc.jodalPlan.joNo from JodalChasu jc) and j.proPlan.proplanNo=:proplanNo " +
            "group by j")
    List<Object[]> stock2(Long proplanNo);

    @EntityGraph(attributePaths = {"proPlan"})
    @Query("select j, sum(jc.joNum) from JodalPlan j " +
            "join JodalChasu jc on (j.joNo=jc.jodalPlan.joNo) " +
            "where j.joNo not in (select c.jodalPlan.joNo from Contract c) and j.joNo in (select jc.jodalPlan.joNo from JodalChasu jc) " +
            "group by j")
    List<Object[]> noneContractJodalPlan();

    @Query("select count(j) from JodalPlan j where j.joNo not in (select jc.jodalPlan.joNo from JodalChasu jc)")
    Long newNoneJodalPlanCount();

    @Query("select j, p, pp from JodalPlan j " +
            "join Product p on (j.proPlan.product.manuCode=p.manuCode) join ProPlan pp on (j.proPlan.proplanNo=pp.proplanNo) " +
            "where j.joNo=:joNo")
    List<Object[]> getJodalPlan(Long joNo);

    @EntityGraph(attributePaths = {"proPlan"})
    @Query("select j, p from JodalPlan j " +
            "join Product p on (j.proPlan.product.manuCode=p.manuCode) " +
            "where j.joNo not in (select c.jodalPlan.joNo from Contract c) " +
            "and j.joNo not in (select jc.jodalPlan.joNo from JodalChasu jc)")
    Page<Object[]> noContract(Pageable pageable);

}
