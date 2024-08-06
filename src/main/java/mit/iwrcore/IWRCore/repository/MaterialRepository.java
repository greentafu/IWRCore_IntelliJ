package mit.iwrcore.IWRCore.repository;


import mit.iwrcore.IWRCore.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
