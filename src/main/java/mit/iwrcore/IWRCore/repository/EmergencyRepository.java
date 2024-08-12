package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EmergencyRepository extends JpaRepository<Emergency , Long> {
}
