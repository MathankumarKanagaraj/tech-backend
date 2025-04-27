package com.Tech.Project.FeignClients;

import com.Tech.Project.Dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8080/api/v1")
public interface UserClient {
    @GetMapping("/users")
    ResponseDto getUserByEmail(@RequestParam String email);
}
