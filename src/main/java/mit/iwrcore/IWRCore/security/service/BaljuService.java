package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Balju;

import java.util.List;
import java.util.Optional;

public interface BaljuService {
    Balju saveBalju(Balju balju); // Balju 저장
    Optional<Balju> findById(Long id); // ID로 Balju 조회
    List<Balju> findAll(); // 모든 Balju 조회
    void deleteById(Long id); // ID로 Balju 삭제
}
