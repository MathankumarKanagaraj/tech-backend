package com.Tech.Project.Controller;

import com.Tech.Project.Dto.ResponseDto;
import com.Tech.Project.Entity.Inventory;
import com.Tech.Project.Service.InventoryService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/inventory")
    public ResponseDto createInventory(@RequestBody Inventory inventory) throws BadRequestException {
        return this.inventoryService.createInventory(inventory);
    }
    @GetMapping("/inventory/{id}")
    public ResponseDto findByInventory(@PathVariable final String id) throws BadRequestException {
        return this.inventoryService.findByInventory(id);
    }

    @GetMapping("/inventory")
    public ResponseDto retriveInventory() {
        return this.inventoryService.retriveInventory();
    }

    @PutMapping("inventory/{id}")
    public ResponseDto UpdateByInventory(@PathVariable final String id, @RequestBody Inventory inventory) throws BadRequestException {
        return this.inventoryService.updateByInventory(id, inventory);
    }
}
