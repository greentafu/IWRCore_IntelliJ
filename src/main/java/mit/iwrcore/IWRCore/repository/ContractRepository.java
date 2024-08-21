package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query("select j, c, jc from JodalPlan j " +
            "left join Contract c on (j.joNo=c.jodalPlan.joNo) " +
            "left join JodalChasu jc on (j.joNo=jc.jodalPlan.joNo) " +
            "where jc.jcnum = (select min(jc2.jcnum) from JodalChasu jc2 where jc2.jodalPlan.joNo = j.joNo)")
    Page<Object[]> yesplanMaterial(Pageable pageable);

    @Query("select j, c, jc from JodalPlan j " +
            "left join Contract c on (j.joNo=c.jodalPlan.joNo) " +
            "left join JodalChasu jc on (j.joNo=jc.jodalPlan.joNo) " +
            "where jc.jcnum = (select min(jc2.jcnum) from JodalChasu jc2 where jc2.jodalPlan.joNo = j.joNo) " +
            "and j.joNo not in (select c2.jodalPlan.joNo from Contract c2)")
    Page<Object[]> couldContractMaterial(Pageable pageable);

    @Query("select c from Contract c where c.partner.pno=:pno")
    Page<Contract> partnerContractList(Pageable pageable, Long pno);

//    @Query("select c from Contract c " +
//            "left join Balju b on (c.conNo=b.contract.conNo) " +
//            "where b.baljuNo is null and c.partner.pno=:pno")
//    List<Contract> newOrderContract(Long pno);
    @Query("select c, jc from Contract c " +
            "left join Balju b on (c.conNo=b.contract.conNo) " +
            "left join JodalChasu jc on (c.jodalPlan.joNo=jc.jodalPlan.joNo) " +
            "where b.baljuNo is null and c.partner.pno=:pno")
    List<Object[]> newOrderContract(Long pno);
}
