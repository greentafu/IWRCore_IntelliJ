package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface  ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.manuCode =:pno")
    Product findProduct(Long pno);

    @Query("select p from Product p")
    List<Product> findAllProduct();
}
