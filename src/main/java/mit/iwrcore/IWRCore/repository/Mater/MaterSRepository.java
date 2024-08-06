package mit.iwrcore.IWRCore.repository.Mater;

import mit.iwrcore.IWRCore.entity.MaterS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterSRepository extends JpaRepository<MaterS, Long> {
}
