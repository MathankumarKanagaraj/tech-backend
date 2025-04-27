package com.Tech.Project.FeignClients;

import com.Tech.Project.Dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8080/api/v1")
public interface ProductClient {

    @GetMapping("/retrive-product/{id}")
    ResponseDto getProductById(@PathVariable("id") String id);
}
