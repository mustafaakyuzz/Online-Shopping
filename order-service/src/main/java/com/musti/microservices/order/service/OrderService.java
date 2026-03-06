package com.musti.microservices.order.service;

import com.musti.microservices.order.client.InventoryClient;
import com.musti.microservices.order.dto.OrderRequest;
import com.musti.microservices.order.model.Order;
import com.musti.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest){
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(isProductInStock){
            // map orderRequest to order objects
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());

            // save Order to OrderRepository
            orderRepository.save(order);
        }else {
            throw new RuntimeException("Product with SkuCode" + orderRequest.skuCode() + "is not in stock");
        }
    }
}
