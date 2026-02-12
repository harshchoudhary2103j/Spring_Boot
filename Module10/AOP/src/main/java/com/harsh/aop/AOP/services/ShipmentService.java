package com.harsh.aop.AOP.services;

public interface ShipmentService {
    String orderPackage(Long orderId);
    String trackPackage(Long orderId);
}
