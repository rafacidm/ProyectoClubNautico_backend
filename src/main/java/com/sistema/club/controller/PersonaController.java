package com.sistema.club.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.club.dto.PersonaDTO;
import com.sistema.club.services.PersonaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v0/personas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@PostMapping
	public ResponseEntity<PersonaDTO> crearPersona(@Valid @RequestBody PersonaDTO personaDTO){
		return new ResponseEntity<>(personaService.crearPersona(personaDTO), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<PersonaDTO>> getAllPersonas(){
		return ResponseEntity.ok(personaService.getAllPersonas());
	}
	
	@GetMapping("/sinBarco")
	public ResponseEntity<List<PersonaDTO>> getAllPersonasSinBarco(){
		return ResponseEntity.ok(personaService.getAllPersonasSinBarco());
	}
	
	@GetMapping("/patrones")
	public ResponseEntity<List<PersonaDTO>> getAllPatrones(){
		return ResponseEntity.ok(personaService.getAllPatrones());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PersonaDTO> getPersonaById(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(personaService.getPersonaById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PersonaDTO> modificarPersona(@Valid @RequestBody PersonaDTO personaDTO, @PathVariable(name = "id") Long id){
		PersonaDTO personaModificada = personaService.modificarPersona(personaDTO, id);
		return new ResponseEntity<>(personaModificada, HttpStatus.OK);
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePersonaById(@PathVariable(name = "id") Long id){
		personaService.eliminarPersona(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
