package com.sistema.club.services;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistema.club.dao.UsuarioDAO;
import com.sistema.club.dto.JWTAuthResponseDTO;
import com.sistema.club.dto.SignInRequestDTO;
import com.sistema.club.dto.SignUpRequestDTO;
import com.sistema.club.model.Rol;
import com.sistema.club.model.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final UsuarioDAO usuarioDao;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	public Usuario signup(SignUpRequestDTO signUpRequestDTO) {
		Usuario usuario = new Usuario();
		usuario.setEmail(signUpRequestDTO.getEmail());
		usuario.setApellidos(signUpRequestDTO.getApellidos());
		usuario.setNombre(signUpRequestDTO.getNombre());
		usuario.setUsername(signUpRequestDTO.getUsername());
		usuario.setRol(Rol.USER);
		usuario.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
		
		return usuarioDao.save(usuario);
	}
	
	public JWTAuthResponseDTO signin(SignInRequestDTO signInRequestDTO) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						signInRequestDTO.getUsername(), 
						signInRequestDTO.getPassword()));
		var usuario = usuarioDao.findByUsername(signInRequestDTO.getUsername())
				.orElseThrow(()->new IllegalArgumentException("Usuario o contraseña incorrectos / no existen"));
		var jwt = jwtService.generateToken(usuario);
		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), usuario);
		
		JWTAuthResponseDTO jwtAuthResponseDTO = new JWTAuthResponseDTO();
		
		jwtAuthResponseDTO.setToken(jwt);
		jwtAuthResponseDTO.setRefreshToken(refreshToken);
		return jwtAuthResponseDTO;
	}
}
