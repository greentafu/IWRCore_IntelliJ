package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
