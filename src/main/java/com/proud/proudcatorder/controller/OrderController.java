package com.proud.proudcatorder.controller;

import com.proud.proudcatorder.dto.OrderItemRequest;
import com.proud.proudcatorder.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody List<OrderItemRequest> orderItemRequests) {
        Long orderId = orderService.create(orderItemRequests);
        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }
}
