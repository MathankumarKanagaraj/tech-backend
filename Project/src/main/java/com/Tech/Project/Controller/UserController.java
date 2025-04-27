package com.Tech.Project.Controller;

import com.Tech.Project.Dto.LoginRequest;
import com.Tech.Project.Dto.ResponseDto;
import com.Tech.Project.Dto.SignUpRequest;
import com.Tech.Project.Entity.User;
import com.Tech.Project.Service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseDto createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignUpRequest signUpRequest) {
        return this.userService.signup(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginRequest loginRequest) {
        return this.userService.login(loginRequest);
    }

    @GetMapping("/user/{id}")
    public ResponseDto getUserById(@PathVariable String id) throws BadRequestException {
        return this.userService.getUserById(id);
    }

    @GetMapping("/user")
    public ResponseDto retriveUserById() throws BadRequestException {
        return this.userService.retriveUser();
    }

    @GetMapping("/users")
    public ResponseDto getUserByEmail(@RequestParam String email) throws BadRequestException {
        return this.userService.getUserByEmail(email);
    }
}
