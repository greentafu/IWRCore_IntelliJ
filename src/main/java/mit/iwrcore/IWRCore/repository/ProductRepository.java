package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ProductRepository extends JpaRepository<Product, Long> {
}
