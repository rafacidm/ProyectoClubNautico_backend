package com.sistema.club.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.club.dao.PersonaDAO;
import com.sistema.club.dto.PersonaDTO;
import com.sistema.club.exceptions.ResourceAlreadyDeletedException;
import com.sistema.club.exceptions.ResourceNotFoundException;
import com.sistema.club.model.Persona;

@Service
public class PersonaServiceImpl implements PersonaService{

	@Autowired
	private ModelMapper modelMapper;
	
	private PersonaDTO mapToDTO(Persona persona) {
		PersonaDTO personaDTO = modelMapper.map(persona, PersonaDTO.class);
		return personaDTO;
	}
	
	private Persona mapToEntity(PersonaDTO personaDTO) {
		Persona persona = modelMapper.map(personaDTO, Persona.class);
		return persona;
	}
	
	@Autowired
	private PersonaDAO personaDao;
	
	@Override
	public PersonaDTO crearPersona(PersonaDTO personaDTO) {	
		Persona nuevaPersona = personaDao.save(mapToEntity(personaDTO));
		return mapToDTO(nuevaPersona);
	}

	@Override
	public List<PersonaDTO> getAllPersonas() {
		List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		personaDao.findAll().forEach(p -> personas.add(mapToDTO(p)));
		return personas;
	}

	@Override
	public PersonaDTO getPersonaById(Long id) {
		return mapToDTO(personaDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Persona", "id", id.toString())));
	}

	@Override
	public PersonaDTO modificarPersona(PersonaDTO personaDTO, Long id) {
		Persona persona = personaDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Persona", "id", id.toString()));
		personaDTO.setId(persona.getId());		
		Persona personaModificada = personaDao.save(mapToEntity(personaDTO));
		return mapToDTO(personaModificada);
	}

	@Override
	public void eliminarPersona(Long id) {
		Persona persona = personaDao.findById(id).orElseThrow(()->new ResourceAlreadyDeletedException("Persona", "id", id.toString()));
		if(persona.getId() == id) {			
			personaDao.deleteById(id);
		}
	}

	@Override
	public List<PersonaDTO> getAllPersonasSinBarco() {
		List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		personaDao.findByBarcoIsNull().forEach(p -> personas.add(mapToDTO(p)));
		return personas;
	}

	@Override
	public List<PersonaDTO> getAllPatrones() {
		List<PersonaDTO> patrones = new ArrayList<PersonaDTO>();
		personaDao.findByEsPatronIsTrue().forEach(p -> patrones.add(mapToDTO(p)));
		return patrones;
	}

}
