package com.proud.proudcatorder.controller;

import com.proud.proudcatorder.dto.OrderDetailResponse;
import com.proud.proudcatorder.dto.OrderItemRequest;
import com.proud.proudcatorder.dto.OrderRequest;
import com.proud.proudcatorder.dto.OrderResponse;
import com.proud.proudcatorder.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "상품 단건 주문")
    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody OrderItemRequest orderItemRequest) {
        Long orderId = orderService.create(orderItemRequest);
        return ResponseEntity.created(URI.create("/orders/" + orderId)).body(orderId);
    }

    @Operation(summary = "상품 다건 주문")
    @PostMapping("/bulk-orders")
    public ResponseEntity<Long> createCartItemOrders(@RequestBody OrderRequest orderRequest) {
        Long orderId = orderService.createOrders(orderRequest);
        return ResponseEntity.created(URI.create("/orders/" + orderId)).body(orderId);
    }

    @Operation(summary = "주문 목록 조회")
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrder() {
        List<OrderResponse> orders = orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "주문 상세 조회")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailResponse> getOrderById(@PathVariable(name = "orderId") Long orderId) {
        OrderDetailResponse order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
}
