package com.Tech.Project.Dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String userName;
    private String email;
    private String role;
    private String password;
}
