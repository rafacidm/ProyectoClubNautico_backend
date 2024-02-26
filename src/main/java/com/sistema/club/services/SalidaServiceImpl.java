package com.sistema.club.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.club.dao.BarcoDAO;
import com.sistema.club.dao.PersonaDAO;
import com.sistema.club.dao.SalidaDAO;
import com.sistema.club.dto.SalidaDTO;
import com.sistema.club.exceptions.NotPatronException;
import com.sistema.club.exceptions.ResourceAlreadyDeletedException;
import com.sistema.club.exceptions.ResourceNotFoundException;
import com.sistema.club.model.Barco;
import com.sistema.club.model.Persona;
import com.sistema.club.model.Salida;

@Service
public class SalidaServiceImpl implements SalidaService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	private SalidaDTO mapToDTO(Salida salida) {
		SalidaDTO salidaDTO = modelMapper.map(salida, SalidaDTO.class);
		return salidaDTO;
	}
	
	private Salida mapToEntity(SalidaDTO salidaDTO) {
		Salida salida = modelMapper.map(salidaDTO, Salida.class);
		return salida;
	}
	
	@Autowired
	private SalidaDAO salidaDao;
	@Autowired
	private BarcoDAO barcoDao;
	@Autowired
	private PersonaDAO personaDao;
	
	@Override
	public SalidaDTO crearSalida(Long barcoId, Long patronId, SalidaDTO salidaDTO) {
		Salida salida = mapToEntity(salidaDTO);
		Barco barco = barcoDao.findById(barcoId)
				.orElseThrow(()-> new ResourceNotFoundException("Barco", "id", barcoId.toString()));
		Persona persona = personaDao.findById(patronId)
				.orElseThrow(()-> new ResourceNotFoundException("Persona", "id", patronId.toString()));
		if(persona.getEsPatron() != true) {
			throw new NotPatronException("Persona", "id", persona.getId().toString());
		}
		salida.setBarco(barco);
		salida.setPatron(persona);
		Salida nuevaSalida = salidaDao.save(salida);
		return mapToDTO(nuevaSalida);
	}

	@Override
	public List<SalidaDTO> getAllSalidas() {
		List<SalidaDTO> salidas = new ArrayList<SalidaDTO>();
		salidaDao.findAll().forEach(s -> salidas.add(mapToDTO(s)));
		return salidas;
	}

	@Override
	public SalidaDTO getSalidaById(Long id) {
		return mapToDTO(salidaDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Salida", "id", id.toString())));
	}

	@Override
	public SalidaDTO modificarSalida(SalidaDTO salidaDTO, Long id) {
		Salida salida = salidaDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Salida", "id", id.toString()));
		if(salidaDTO.getPatron().getEsPatron() != true) {
			throw new NotPatronException("Persona", "id", salidaDTO.getPatron().getDni().toString());
		}
		salidaDTO.setId(salida.getId());	
		Salida salidaModificada = salidaDao.save(mapToEntity(salidaDTO));
		return mapToDTO(salidaModificada);
	}

	@Override
	public void eliminarSalida(Long id) {
		Salida salida = salidaDao.findById(id).orElseThrow(()->new ResourceAlreadyDeletedException("Salida", "id", id.toString()));
		if(salida.getId() == id) {			
			salidaDao.deleteById(id);
		}
	}

	@Override
	public List<SalidaDTO> getSalidasByBarcoId(Long barcoId) {
		List<Salida> salidas = salidaDao.findByBarcoId(barcoId);
		return salidas.stream().map(s -> mapToDTO(s)).collect(Collectors.toList());
	}

	@Override
	public List<SalidaDTO> getSalidasByPatronId(Long patronId) {
		List<Salida> salidas = salidaDao.findByPatronId(patronId);
		return salidas.stream().map(s -> mapToDTO(s)).collect(Collectors.toList());
	}

	
}
