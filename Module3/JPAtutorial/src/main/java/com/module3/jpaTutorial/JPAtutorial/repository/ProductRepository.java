package com.module3.jpaTutorial.JPAtutorial.repository;

import com.module3.jpaTutorial.JPAtutorial.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity>findByCreatedAtAfter(LocalDateTime after);

    List<ProductEntity> findByPriceAndQuantity(BigDecimal bigDecimal, Integer i);
}
