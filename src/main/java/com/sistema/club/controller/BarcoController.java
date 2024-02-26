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

import com.sistema.club.dto.BarcoDTO;
import com.sistema.club.services.BarcoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v0/barcos")
@CrossOrigin(origins = "http://localhost:4200")
public class BarcoController {

	@Autowired
	private BarcoService barcoService;
	
	@PostMapping
	public ResponseEntity<BarcoDTO> crearBarco(@Valid @RequestBody BarcoDTO barcoDTO){
		return new ResponseEntity<>(barcoService.crearBarco(barcoDTO), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<BarcoDTO>> getAllBarcos(){
		return ResponseEntity.ok(barcoService.getAllBarcos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BarcoDTO> getBarcoById(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(barcoService.getBarcoById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BarcoDTO> modificarBarco(@Valid @RequestBody BarcoDTO barcoDTO, @PathVariable(name = "id") Long id){
		BarcoDTO barcoModificado = barcoService.modificarBarco(barcoDTO, id);
		return new ResponseEntity<>(barcoModificado, HttpStatus.OK);
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBarcoById(@PathVariable(name = "id") Long id){
		barcoService.eliminarBarco(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/persona/{id}")
	public ResponseEntity<BarcoDTO> getBarcoByPersonaId(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(barcoService.getBarcoByPersonaId(id));
	}

}
