package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Returns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnsRepository extends JpaRepository<Returns,Long> {
}
