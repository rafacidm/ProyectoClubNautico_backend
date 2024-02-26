package com.sistema.club.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.club.dto.JWTAuthResponseDTO;
import com.sistema.club.dto.SignInRequestDTO;
import com.sistema.club.dto.SignUpRequestDTO;
import com.sistema.club.model.Usuario;
import com.sistema.club.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v0/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<Usuario> signup(@RequestBody SignUpRequestDTO signUpRequestDTO){
		return ResponseEntity.ok(authService.signup(signUpRequestDTO));
	}
	
	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponseDTO> signin (@RequestBody SignInRequestDTO signInRequestDTO){
		return ResponseEntity.ok(authService.signin(signInRequestDTO));
	}
}
