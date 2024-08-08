package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StructureRepository extends JpaRepository<Structure,Long> {
    // 제품 ID로 구조 목록을 조회
    List<Structure> findByProduct_ManuCode(Long manuCode);

}
