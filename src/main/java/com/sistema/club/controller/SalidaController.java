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

import com.sistema.club.dto.SalidaDTO;
import com.sistema.club.services.SalidaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v0/salidas")
@CrossOrigin(origins = "http://localhost:4200")
public class SalidaController {

	@Autowired
	private SalidaService salidaService;
	
	@PostMapping
	public ResponseEntity<SalidaDTO> crearSalida(@RequestBody SalidaDTO salidaDTO){
		return new ResponseEntity<>(salidaService.crearSalida(salidaDTO.getBarco().getId(), salidaDTO.getPatron().getId(), salidaDTO), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<SalidaDTO>> getAllSalidas(){
		return ResponseEntity.ok(salidaService.getAllSalidas());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SalidaDTO> getSalidaById(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(salidaService.getSalidaById(id));
	}
	
	@GetMapping("/barco/{barcoId}")
	public ResponseEntity<List<SalidaDTO>> getAllSalidasByBarcoId(@PathVariable(name="barcoId") Long id){
		return ResponseEntity.ok(salidaService.getSalidasByBarcoId(id));
	}
	
	@GetMapping("/persona/{patronId}")
	public ResponseEntity<List<SalidaDTO>> getAllSalidasByPatronId(@PathVariable(name="patronId") Long id){
		return ResponseEntity.ok(salidaService.getSalidasByPatronId(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SalidaDTO> modificarSalida(@Valid @RequestBody SalidaDTO salidaDTO, @PathVariable(name = "id") Long id){
		SalidaDTO salidaModificada = salidaService.modificarSalida(salidaDTO, id);
		return new ResponseEntity<>(salidaModificada, HttpStatus.OK);
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSalidaById(@PathVariable(name = "id") Long id){
		salidaService.eliminarSalida(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
