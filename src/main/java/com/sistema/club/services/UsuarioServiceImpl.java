package com.sistema.club.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.club.dao.UsuarioDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

	private final UsuarioDAO usuarioDao;
	
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username){
				return usuarioDao.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
			}
		};
	}
}
