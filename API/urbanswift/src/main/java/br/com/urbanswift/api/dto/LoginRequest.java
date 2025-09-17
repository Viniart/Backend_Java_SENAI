package br.com.urbanswift.api.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String senha;
}
