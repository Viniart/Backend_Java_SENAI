package br.com.urbanswift.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String token;
    private final String tokenType = "Bearer";
}
