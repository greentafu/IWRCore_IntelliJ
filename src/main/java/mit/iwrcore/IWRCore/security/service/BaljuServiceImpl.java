package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Balju;
import mit.iwrcore.IWRCore.repository.BaljuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BaljuServiceImpl implements BaljuService{
    private final BaljuRepository baljuRepository;

    @Autowired
    public BaljuServiceImpl(BaljuRepository baljuRepository) {
        this.baljuRepository = baljuRepository;
    }

    @Override
    public Balju saveBalju(Balju balju) {
        return baljuRepository.save(balju);
    }

    @Override
    public Optional<Balju> findById(Long id) {
        return baljuRepository.findById(id);
    }

    @Override
    public List<Balju> findAll() {
        return baljuRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        baljuRepository.deleteById(id);
    }
}
