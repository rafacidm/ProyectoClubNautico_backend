package com.sistema.club.services;

import java.util.List;

import com.sistema.club.dto.BarcoDTO;

public interface BarcoService {
	public BarcoDTO crearBarco(BarcoDTO barcoDTO);
	public List<BarcoDTO> getAllBarcos();
	public BarcoDTO getBarcoById(Long id);
	public BarcoDTO getBarcoByPersonaId(Long personaId);
	public BarcoDTO modificarBarco(BarcoDTO barcoDTO, Long id);
	public void eliminarBarco(Long id);
}
