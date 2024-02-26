package com.sistema.club.services;

import com.sistema.club.dto.JWTAuthResponseDTO;
import com.sistema.club.dto.SignInRequestDTO;
import com.sistema.club.dto.SignUpRequestDTO;
import com.sistema.club.model.Usuario;

public interface AuthService {

	public Usuario signup(SignUpRequestDTO signUpRequestDTO);
	public JWTAuthResponseDTO signin(SignInRequestDTO signInRequestDTO);
}
