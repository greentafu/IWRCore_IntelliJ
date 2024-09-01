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

    @Query("select j, c, jc, sum(jc.joNum) from JodalPlan j " +
            "left join Contract c on (j.joNo=c.jodalPlan.joNo) " +
            "left join JodalChasu jc on (j.joNo=jc.jodalPlan.joNo) " +
            "where jc.jcnum = (select min(jc2.jcnum) from JodalChasu jc2 where jc2.jodalPlan.joNo = j.joNo) " +
            "group by j")
    Page<Object[]> yesplanMaterial(Pageable pageable);

    @Query("select j, c, jc, sum(jc.joNum) from JodalPlan j " +
            "left join Contract c on (j.joNo=c.jodalPlan.joNo) " +
            "left join JodalChasu jc on (j.joNo=jc.jodalPlan.joNo) " +
            "where jc.jcnum = (select min(jc2.jcnum) from JodalChasu jc2 where jc2.jodalPlan.joNo = j.joNo) " +
            "and j.joNo not in (select cc.jodalPlan.joNo from Contract cc) " +
            "group by j")
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

    @Query("select m2, c2.money, sum(sh.shipNum), sum(re.requestNum), count(b) from Material m2 " +
            "left join Contract c2 on c2.conNo = (select max(c1.conNo) from Contract c1 " +
            "where c1.jodalPlan.material.materCode = m2.materCode) " +
            "left join Shipment sh on (sh.balju.contract.jodalPlan.material.materCode=m2.materCode and sh.receiveCheck=1) " +
            "left join Request re on (re.material.materCode=m2.materCode and re.reqCheck=1) " +
            "left join Balju b on (b.contract.jodalPlan.material.materCode=m2.materCode and b.finCheck=0) " +
            "group by m2")
    List<Object[]> stockList();

    @Query("select c, sum(sh.shipNum), sum(re.requestNum) from Contract c " +
            "left join Shipment sh on (sh.balju.contract.jodalPlan.material.materCode=c.jodalPlan.material.materCode and sh.receiveCheck=1 and c.conNo>=sh.balju.contract.conNo) " +
            "left join Request re on (re.material.materCode=c.jodalPlan.material.materCode and re.reqCheck=1 and c.jodalPlan.proPlan.proplanNo>=re.proPlan.proplanNo) " +
            "where c.jodalPlan.material.materCode=:materCode " +
            "group by c.conNo order by c.conNo desc")
    List<Object[]> detailStock(Long materCode);
}
