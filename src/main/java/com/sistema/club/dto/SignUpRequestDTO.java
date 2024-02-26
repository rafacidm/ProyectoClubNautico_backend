package com.sistema.club.dto;

import lombok.Data;

@Data
public class SignUpRequestDTO {

	private String nombre;
	private String apellidos;
	private String email;
	private String username;
	private String password;
}
