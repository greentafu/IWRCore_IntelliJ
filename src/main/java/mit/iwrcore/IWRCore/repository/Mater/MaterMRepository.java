package mit.iwrcore.IWRCore.repository.Mater;

import mit.iwrcore.IWRCore.entity.MaterM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterMRepository extends JpaRepository<MaterM, Long> {
}
