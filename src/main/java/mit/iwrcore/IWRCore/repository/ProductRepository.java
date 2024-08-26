package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.Product;
import org.springframework.data.domain.Page;
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

    // 생산계획 없는 제품 리스트
    @Query(value = "select p from Product p where p.manuCode not in (select pp.product.manuCode from ProPlan pp) and p.mater_imsi=1 and p.mater_check=1")
    Page<Product> findNonPlanProduct(Pageable pageable);
    // 임시저장 했으나 최종확인은 하지 않은 제품 리스트
    @Query("select p from Product p where p.mater_imsi=1 and p.mater_check=0")
    Page<Product> findNonCheckProduct(Pageable pageable);
    // 최종확인한 제품 리스트
    @Query("select p from Product p where p.mater_check=1")
    Page<Product> findCheckProduct(Pageable pageable);


    @Query("select count(p) from Product p where p.mater_imsi=1 and p.mater_check=0")
    Long newProductCount();
}
