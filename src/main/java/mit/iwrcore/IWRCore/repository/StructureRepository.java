package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Product;
import mit.iwrcore.IWRCore.entity.Structure;
import mit.iwrcore.IWRCore.security.dto.StructureDTO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StructureRepository extends JpaRepository<Structure,Long> {
    // 제품 ID로 구조 목록을 조회
    List<Structure> findByProduct_ManuCode(Long manuCode);
}
