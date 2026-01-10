package com.module3.jpaTutorial.JPAtutorial;

import com.module3.jpaTutorial.JPAtutorial.entities.ProductEntity;
import com.module3.jpaTutorial.JPAtutorial.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class JpAtutorialApplicationTests {
    @Autowired
    ProductRepository productRepository;
	@Test
	void contextLoads() {
	}
    @Test
    void testRepository(){
        ProductEntity productEntity = ProductEntity
                .builder()
                .sku("nestle2")
                .title("NestleChocolate1")
                .price(BigDecimal.valueOf(123.45))
                .quantity(12)
                .build();
        ProductEntity savedProductEntity = productRepository.save(productEntity);
        System.out.println(savedProductEntity);


    }
    @Test
    void getEntities(){
//        List<ProductEntity>entities = productRepository.findByCreatedAtAfter(LocalDateTime.of(2026,1,1,0,0,0));
        List<ProductEntity>entities = productRepository.findByPriceAndQuantity(BigDecimal.valueOf(123.45),12);
        System.out.println(entities);
    }

}
