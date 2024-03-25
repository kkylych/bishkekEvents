package com.example.springmid.dto.reuest;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerRequestDTO {
    @NotBlank(message = "Username can not be empty")
    private String username;

    @NotBlank(message = "Email can not be empty")
    private String email;

    @NotBlank(message = "Password can not be empty")
    private String password;
}
