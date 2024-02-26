package com.sistema.club.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.club.dao.BarcoDAO;
import com.sistema.club.dto.BarcoDTO;
import com.sistema.club.exceptions.ResourceAlreadyDeletedException;
import com.sistema.club.exceptions.ResourceNotFoundException;
import com.sistema.club.model.Barco;

@Service
public class BarcoServiceImpl implements BarcoService{

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BarcoDAO barcoDao;
	
	private BarcoDTO mapToDTO(Barco barco) {
		BarcoDTO barcoDTO = new BarcoDTO();
		barcoDTO.setAtraque(barco.getAtraque());
		barcoDTO.setCuota(barco.getCuota());
		barcoDTO.setId(barco.getId());
		barcoDTO.setMatricula(barco.getMatricula());
		barcoDTO.setNombre(barco.getNombre());
		barcoDTO.setPersona(barco.getPersona());
		return barcoDTO;
	}
	
	private Barco mapToEntity(BarcoDTO barcoDTO) {
		Barco barco = modelMapper.map(barcoDTO, Barco.class);
		return barco;
	}

	@Override
	public BarcoDTO crearBarco(BarcoDTO barcoDTO){
		Barco barco = mapToEntity(barcoDTO);
		Barco nuevoBarco = barcoDao.save(barco);
		return mapToDTO(nuevoBarco);
	}

	@Override
	public List<BarcoDTO> getAllBarcos() {
		List<BarcoDTO> barcos = new ArrayList<BarcoDTO>();
		barcoDao.findAll().forEach(b -> barcos.add(mapToDTO(b)));
		return barcos;
	}

	@Override
	public BarcoDTO getBarcoById(Long id) {
		return mapToDTO(barcoDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Barco", "id", id.toString())));
	}

	@Override
	public BarcoDTO modificarBarco(BarcoDTO barcoDTO, Long id) {
		Barco barco = barcoDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Barco", "id", id.toString()));
		barco.setPersona(barcoDTO.getPersona());
		barco.setAtraque(barcoDTO.getAtraque());
		barco.setCuota(barcoDTO.getCuota());
		barco.setMatricula(barcoDTO.getMatricula());
		barco.setNombre(barcoDTO.getNombre());	
		
		Barco barcoModificado = barcoDao.save(barco);
		return mapToDTO(barcoModificado);
	}

	@Override
	public void eliminarBarco(Long id) {
		Barco barco = barcoDao.findById(id).orElseThrow(()->new ResourceAlreadyDeletedException("Barco", "id", id.toString()));
		if(barco.getId() == id) {			
			barcoDao.deleteById(id);
		}
	}

	@Override
	public BarcoDTO getBarcoByPersonaId(Long personaId) {
		return mapToDTO(barcoDao.findByPersona_Id(personaId));
	}
}
