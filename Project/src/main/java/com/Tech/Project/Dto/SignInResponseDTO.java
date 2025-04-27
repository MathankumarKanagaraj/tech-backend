package com.Tech.Project.Dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInResponseDTO {

    private String token;
    private String refreshToken;
    private Date expiresIn;
}