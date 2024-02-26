package com.sistema.club.dto;

import lombok.Data;

@Data
public class JWTAuthResponseDTO {

	private String token;
	private String refreshToken;
}
