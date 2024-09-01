package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request , Long> {
    List<Request> findByReqCheck(Long reqCheck);

    @Query("select count(r) from Request r where r.reqCheck=0")
    Long mainRequestCount();
}
