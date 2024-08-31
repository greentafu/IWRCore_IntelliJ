package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.JodalChasu;
import mit.iwrcore.IWRCore.security.dto.multiDTO.QuantityDateDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JodalChasuRepository extends JpaRepository<JodalChasu, Long> {
    @Query("select j from JodalChasu j where j.jodalPlan.joNo=:jpnum")
    List<JodalChasu> getJodalChausFromPlan(Long jpnum);

    @Query("select j, st, sum(r.requestNum), sum(sh.shipNum), jc, count(c) from JodalPlan j " +
            "join Structure st on (j.material.materCode=st.material.materCode and j.proPlan.product.manuCode=st.product.manuCode) " +
            "left join Request r on (j.material.materCode=r.material.materCode and r.reqCheck=1) " +
            "left join JodalChasu jc on (j.joNo=jc.jodalPlan.joNo) " +
            "left join Shipment sh on (j.joNo=sh.balju.contract.jodalPlan.joNo and sh.receiveCheck=1) " +
            "left join Contract c on (c.jodalPlan.joNo=j.joNo) " +
            "where j.proPlan.proplanNo=:proplanNo " +
            "group by j, jc")
    List<Object[]> modifyJodalChasu(Long proplanNo);

    @Query("select jc from JodalChasu jc where jc.jodalPlan.joNo=:joNo")
    List<JodalChasu> findJCfromJP(Long joNo);

}
