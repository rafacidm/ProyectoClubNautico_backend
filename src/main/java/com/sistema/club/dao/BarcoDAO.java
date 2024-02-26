package com.sistema.club.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sistema.club.model.Barco;

@Repository
public interface BarcoDAO extends CrudRepository<Barco, Long>{

	public Barco findByPersona_Id(Long personaId);
}
