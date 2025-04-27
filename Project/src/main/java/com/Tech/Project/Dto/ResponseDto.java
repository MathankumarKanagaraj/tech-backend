package com.Tech.Project.Dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private Object data;
    private Integer statusCode;
}
