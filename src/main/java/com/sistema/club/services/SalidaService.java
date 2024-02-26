package com.sistema.club.services;

import java.util.List;

import com.sistema.club.dto.SalidaDTO;

public interface SalidaService {

	public SalidaDTO crearSalida(Long barcoId, Long patronId, SalidaDTO salidaDTO);
	public List<SalidaDTO> getAllSalidas();
	public SalidaDTO getSalidaById(Long id);
	public List<SalidaDTO> getSalidasByBarcoId(Long barcoId);
	public List<SalidaDTO> getSalidasByPatronId(Long patronId);
	public SalidaDTO modificarSalida(SalidaDTO salidaDTO, Long id);
	public void eliminarSalida(Long id);
}
