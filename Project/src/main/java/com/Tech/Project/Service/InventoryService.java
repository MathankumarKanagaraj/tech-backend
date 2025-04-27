package com.Tech.Project.Service;

import com.Tech.Project.Dto.ResponseDto;
import com.Tech.Project.Entity.Inventory;
import com.Tech.Project.Repository.InventoryRepository;
import com.Tech.Project.Utils.Constants;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public ResponseDto createInventory(Inventory inventory) {
        return new ResponseDto(Constants.CREATED, this.inventoryRepository.save(inventory), HttpStatus.CREATED.value());
    }

    public ResponseDto findByInventory(String id) throws BadRequestException {
        Optional<Inventory> inventory = Optional.ofNullable(this.inventoryRepository.findById(id).orElseThrow(() -> new BadRequestException("Data not found.")));
        return new ResponseDto(Constants.RETRIEVED, inventory, HttpStatus.OK.value());
    }

    public ResponseDto updateByInventory(String id, Inventory updatedInventory) throws BadRequestException {
        Inventory existingInventory = inventoryRepository.findByProductId(updatedInventory.getProductId())
                .orElseThrow(() -> new BadRequestException("Data not found."));
        existingInventory.setQuantity(updatedInventory.getQuantity());
        existingInventory.setProductId(updatedInventory.getProductId());
        Inventory savedInventory = inventoryRepository.save(existingInventory);
        return new ResponseDto(Constants.UPDATED, savedInventory, HttpStatus.OK.value());
    }

    public ResponseDto retriveInventory() {
        return new ResponseDto(Constants.RETRIEVED,this.inventoryRepository.findAll(),HttpStatus.OK.value());
    }
}
