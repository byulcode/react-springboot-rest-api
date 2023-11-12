package com.proud.proudcatorder.repository;

import com.proud.proudcatorder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
