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

    @Query("select pp, st, sum(r.requestNum), sum(sh.shipNum), j, pro " +
            "from Structure st " +
            "left join ProPlan pp on (st.product.manuCode=pp.product.manuCode) " +
            "left join Request r on (r.material.materCode=st.material.materCode) and r.reqCheck=1 " +
            "left join Shipment sh on (sh.balju.contract.jodalPlan.material.materCode=st.material.materCode) and sh.receiveCheck=1 " +
            "left join JodalPlan j on (j.material.materCode=st.material.materCode) " +
            "left join Product pro on (st.product.manuCode=pro.manuCode) " +
            "where pp.proplanNo=:proplanNo and j.joNo not in (select jc.jodalPlan.joNo from JodalChasu jc) " +
            "group by st")
    List<Object[]> stock(Long proplanNo);

    @EntityGraph(attributePaths = {"proPlan"})
    @Query("select j, p from JodalPlan j " +
            "join Product p on (j.proPlan.product.manuCode=p.manuCode) " +
            "where j.joNo not in (select c.jodalPlan.joNo from Contract c)")
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
