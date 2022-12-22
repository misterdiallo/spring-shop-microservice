package com.misterdiallo.backend.orderservice.repository;

import com.misterdiallo.backend.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
