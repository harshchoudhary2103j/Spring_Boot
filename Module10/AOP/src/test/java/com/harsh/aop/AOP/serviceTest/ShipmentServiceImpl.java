package com.harsh.aop.AOP.serviceTest;

import com.harsh.aop.AOP.services.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
public class ShipmentServiceImpl {
    @Autowired
    private  ShipmentService shipmentService;

    @Test
    void orderPackageTest(){
        String orderString  = shipmentService.orderPackage(1L);
        log.info(orderString);
    }

    @Test
    void trackPackageTest(){
        shipmentService.trackPackage(1L);
    }

}
