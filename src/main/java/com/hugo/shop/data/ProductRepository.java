package com.hugo.shop.data;

import com.hugo.shop.biz.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query(nativeQuery = true, value = "select image_name1 from product where ID = :id")
    public String findImagename1ById(@Param("id") Long id);

    public List<Product> findTop8ByDiscountLessThanOrderByIdAsc(float discount);
}
