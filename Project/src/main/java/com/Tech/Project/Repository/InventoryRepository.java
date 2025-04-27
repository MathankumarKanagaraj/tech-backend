package com.Tech.Project.Repository;

import com.Tech.Project.Entity.Inventory;
import com.Tech.Project.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,String> {
    Optional<Inventory> findByProductId(Product product);
}
