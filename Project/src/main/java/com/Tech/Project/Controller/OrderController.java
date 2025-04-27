package com.Tech.Project.Controller;

import com.Tech.Project.Dto.ResponseDto;
import com.Tech.Project.Entity.Order;
import com.Tech.Project.Entity.User;
import com.Tech.Project.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseDto createOrder(@RequestBody final Order order) throws BadRequestException {
        return this.orderService.createOrder(order);
    }
    @GetMapping("/order/{id}")
    public ResponseDto retriveOrder(@PathVariable final String id) throws BadRequestException {
        return this.orderService.findByOrder(id);
    }
        @GetMapping("order/user/{id}")
    public ResponseDto retriveOrderByUser(@PathVariable final String  id) throws BadRequestException {
        return this.orderService.findByOrderUser(id);
    }
}
