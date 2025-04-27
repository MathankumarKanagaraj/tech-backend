package com.Tech.Project.Service;

import com.Tech.Project.Dto.ResponseDto;
import com.Tech.Project.Entity.Inventory;
import com.Tech.Project.Entity.Order;
import com.Tech.Project.Entity.User;
import com.Tech.Project.FeignClients.InventoryClient;
import com.Tech.Project.FeignClients.ProductClient;
import com.Tech.Project.FeignClients.UserClient;
import com.Tech.Project.Repository.OrderRepository;
import com.Tech.Project.Utils.Constants;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private InventoryClient inventoryClient;
    public ResponseDto createOrder(Order order) throws BadRequestException {
        // Validate product
        ResponseDto productResponse = productClient.getProductById(String.valueOf(order.getProductId()));
        if (productResponse.getStatusCode() != HttpStatus.OK.value()) {
            throw new BadRequestException("Invalid product ID.");
        }

        // Validate user
        ResponseDto userResponse = userClient.getUserByEmail(order.getUserId().getEmail());
        if (userResponse.getStatusCode() != HttpStatus.OK.value()) {
            throw new BadRequestException("Invalid user email.");
        }

        // Check and update stock
        ResponseDto inventoryResponse = inventoryClient.findByInventory(String.valueOf(order.getProductId()));
        if (inventoryResponse.getStatusCode() != HttpStatus.OK.value()) {
            throw new BadRequestException("Product out of stock.");
        }

        // Update inventory
        Inventory inventory = (Inventory) inventoryResponse.getData();
        inventory.setQuantity(inventory.getQuantity() - order.getQuantity());
        inventoryClient.updateByInventory(inventory.getId(), inventory);

        // Save order
        return new ResponseDto(Constants.CREATED, this.orderRepository.save(order), HttpStatus.CREATED.value());
    }

    public ResponseDto findByOrder(String id) throws BadRequestException {
        Optional<Order> order = Optional.ofNullable(this.orderRepository.findById(id).orElseThrow(() -> new BadRequestException("Order not found..")));
        return new ResponseDto(Constants.RETRIEVED,order,HttpStatus.OK.value());
    }

    public ResponseDto findByOrderUser(String id) throws BadRequestException {
        List<Order> orders = this.orderRepository.findByUserId_Id(id);
        return new ResponseDto(Constants.RETRIEVED, orders, HttpStatus.OK.value());
    }
}
