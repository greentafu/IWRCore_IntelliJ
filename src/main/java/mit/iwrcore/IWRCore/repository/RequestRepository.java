package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request , Long> {
    List<Request> findByReqCheck(Long reqCheck);
}
