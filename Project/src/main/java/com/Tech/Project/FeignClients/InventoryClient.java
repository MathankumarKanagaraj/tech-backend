package com.Tech.Project.FeignClients;

import com.Tech.Project.Dto.ResponseDto;
import com.Tech.Project.Entity.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", url = "http://localhost:8080/api/v1")
public interface InventoryClient {
    @GetMapping("/inventory/{id}")
    ResponseDto findByInventory(@PathVariable String id);

    @PutMapping("/inventory/{id}")
    ResponseDto updateByInventory(@PathVariable String id, @RequestBody Inventory inventory);
}
