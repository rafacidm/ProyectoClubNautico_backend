package com.sistema.club.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sistema.club.model.Rol;
import com.sistema.club.model.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Long> {

	Optional<Usuario> findByUsername(String username);
	Usuario findByRol(Rol rol);
}
