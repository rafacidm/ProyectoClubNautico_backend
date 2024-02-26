package com.sistema.club.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.sistema.club.model.Barco;
import com.sistema.club.model.Persona;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SalidaDTO {

	private Long id;
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@PastOrPresent(message="La fecha debe ser pasada.")
	private LocalDate fecha;
	@NotNull
	private LocalTime hora;
	@NotEmpty
	@Size(max=30, message="El nombre no puede contener mas de 30 caracteres.")
	private String destino;
	@NotNull
	private Persona patron;
	@NotNull
	private Barco barco;
	
	public SalidaDTO(Long id, LocalDate fecha, LocalTime hora, String destino) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.destino = destino;
	}

	public SalidaDTO(Long id, LocalDate fecha, LocalTime hora, String destino, Persona patron, Barco barco) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.destino = destino;
		this.patron = patron;
		this.barco = barco;
	}
}
