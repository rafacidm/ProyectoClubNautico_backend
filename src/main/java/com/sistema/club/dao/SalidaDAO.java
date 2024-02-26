package com.sistema.club.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sistema.club.model.Salida;

@Repository
public interface SalidaDAO extends CrudRepository<Salida, Long>{

	public List<Salida> findByBarcoId(Long barcoId);
	public List<Salida> findByPatronId(Long patronId);
}
