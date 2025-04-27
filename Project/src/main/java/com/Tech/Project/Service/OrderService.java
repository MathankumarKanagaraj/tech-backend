package com.Tech.Project.Service;

import com.Tech.Project.Dto.ResponseDto;
import com.Tech.Project.Entity.Order;
import com.Tech.Project.Entity.User;
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
    public ResponseDto createOrder(Order order) {
        return new ResponseDto(Constants.CREATED,this.orderRepository.save(order), HttpStatus.CREATED.value());
    }

    public ResponseDto findByOrder(String id) throws BadRequestException {
        Optional<Order> order = Optional.ofNullable(this.orderRepository.findById(id).orElseThrow(() -> new BadRequestException("Order not found..")));
        return new ResponseDto(Constants.RETRIEVED,order,HttpStatus.OK.value());
    }

    public ResponseDto findByOrderUser(User user) throws BadRequestException {
        List<Order> orders = this.orderRepository.findByUserId(user);
        return new ResponseDto(Constants.RETRIEVED, orders, HttpStatus.OK.value());
    }
}
