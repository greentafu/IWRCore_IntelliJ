package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.ProL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DataToCsvRepository  extends CrudRepository<ProL,Long> {
    List<ProL> findAll();
}
