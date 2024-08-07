package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.ProL;
import mit.iwrcore.IWRCore.entity.ProM;
import mit.iwrcore.IWRCore.entity.ProS;
import mit.iwrcore.IWRCore.security.dto.ProLDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  ProLCodeRepository extends JpaRepository<ProL, Long> {

}
