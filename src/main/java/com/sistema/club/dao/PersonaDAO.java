package com.sistema.club.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sistema.club.model.Persona;

@Repository
public interface PersonaDAO extends CrudRepository<Persona, Long>{
	public List<Persona> findByBarcoIsNull();
	public List<Persona> findByEsPatronIsTrue();
}
