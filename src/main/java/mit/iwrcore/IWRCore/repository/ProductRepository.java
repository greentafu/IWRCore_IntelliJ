package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface  ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.manuCode =:pno")
    Product findProduct(Long pno);


    @Query(value = "select p from Product p", countQuery = "select count(p) from Product p where p.manuCode>:manuCode")
    List<Product> findAllProduct(Pageable pageable);
}
