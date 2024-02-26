package com.sistema.club.services;

import java.util.List;

import com.sistema.club.dto.PersonaDTO;

public interface PersonaService {

	public PersonaDTO crearPersona(PersonaDTO personaDTO);
	public List<PersonaDTO> getAllPersonas();
	public PersonaDTO getPersonaById(Long id);
	public PersonaDTO modificarPersona(PersonaDTO personaDTO, Long id);
	public void eliminarPersona(Long id);
	public List<PersonaDTO> getAllPersonasSinBarco();
	public List<PersonaDTO> getAllPatrones();
}
