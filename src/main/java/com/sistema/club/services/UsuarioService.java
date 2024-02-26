package com.sistema.club.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

	public UserDetailsService userDetailsService();
}
