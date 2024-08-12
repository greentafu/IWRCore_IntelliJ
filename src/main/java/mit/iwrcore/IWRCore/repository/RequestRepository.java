package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request , Long> {
}
