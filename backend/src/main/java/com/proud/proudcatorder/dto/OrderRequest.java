package com.proud.proudcatorder.dto;

import com.proud.proudcatorder.entity.Order;

import java.util.List;

public record OrderRequest (
        String email, String address, String postcode, List<OrderItemRequest> orderItems
){
    public Order toEntity() {
        return Order.builder()
                .email(email)
                .postcode(postcode)
                .address(address)
                .build();
    }
}
