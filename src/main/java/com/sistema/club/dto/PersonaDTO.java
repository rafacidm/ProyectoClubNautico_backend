package com.sistema.club.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonaDTO {

	private Long id;
	@NotEmpty
	@Size(max=30, message="El nombre no puede contener mas de 30 caracteres.")
	private String nombre;
	@NotEmpty
	@Size(max=60, message="El nombre no puede contener mas de 60 caracteres.")
	private String apellidos;
	@NotEmpty
	@Pattern(regexp="^[0-9]{8}[A-Z]{1}", message="El DNI debe tener el siguiente formato: 00000000A")
	private String dni;
	@NotEmpty
	@Pattern(regexp="(6|7)([0-9]){8}", message="El numero de telefono debe tener el formato: 666666666")
	private String telefono;
	@NotNull
	private Boolean esSocio;
	@NotNull
	private Boolean esPatron;
	
	public PersonaDTO(Long id, String nombre, String apellidos, String dni, String telefono, Boolean esSocio,Boolean esPatron) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.esSocio = esSocio;
		this.esPatron = esPatron;
	}
}
