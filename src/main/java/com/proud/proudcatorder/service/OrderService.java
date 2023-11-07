package com.proud.proudcatorder.service;

import com.proud.proudcatorder.dto.OrderItemRequest;
import com.proud.proudcatorder.entity.Order;
import com.proud.proudcatorder.entity.OrderItem;
import com.proud.proudcatorder.entity.Product;
import com.proud.proudcatorder.repository.OrderRepository;
import com.proud.proudcatorder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long create(List<OrderItemRequest> orderItemRequests) {
        Order order = Order.builder().build();
        for (OrderItemRequest item : orderItemRequests) {
            Product product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new NoSuchElementException("Product not found"));

            OrderItem orderItem = item.toEntity(product);
            order.addOrderItem(orderItem);
        }
        return orderRepository.save(order).getId();
    }
}
