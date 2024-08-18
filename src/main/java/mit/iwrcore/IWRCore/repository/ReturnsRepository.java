package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Returns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnsRepository extends JpaRepository<Returns,Long> {
    @Query("select r from Returns r where r.shipment.balju.baljuNo=:baljuNo")
    List<Returns> getReturns(Long baljuNo);
}
