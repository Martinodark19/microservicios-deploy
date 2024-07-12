package com.example.authentication_service.dto;

import lombok.Data;

@Data
public class LoginRequestDto 
{
    private String username;
    private String password;
}
