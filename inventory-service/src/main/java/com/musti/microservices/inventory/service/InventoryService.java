package com.musti.microservices.inventory.service;

import com.musti.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity){
        // Find inventory for a given skuCode where quantity > 0
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
