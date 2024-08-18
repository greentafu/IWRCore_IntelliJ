package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Emergency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmergencyRepository extends JpaRepository<Emergency , Long> {
    @Query("select e from Emergency e where e.balju.contract.partner.pno=:pno")
    Page<Emergency> findEmergency(Pageable pageable, Long pno);

    @Query("select e from Emergency e where e.balju.baljuNo=:baljuNo")
    List<Emergency> getEmengencyListByBalju(Long baljuNo);
}
