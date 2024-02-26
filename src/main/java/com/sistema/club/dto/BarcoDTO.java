package com.sistema.club.dto;

import com.sistema.club.model.Persona;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class BarcoDTO {

	private Long id;
	@NotEmpty
	@Pattern(regexp="[A-Z]{2}[0-9]{6}", message="La matricula debe tener el formato: XX000000")
	private String matricula;
	@NotEmpty
	@Size(max=30, message="El nombre no puede contener mas de 30 caracteres.")
	private String nombre;
	@NotNull
	@Min(100)
	@Max(799)
	private Integer atraque;
	@NotNull
	@Min(100)
	@Max(1999)
	private Double cuota;
	@NotNull
	private Persona persona;
		
	public BarcoDTO(Long id, String matricula, String nombre, Integer atraque, Double cuota, Persona persona) {
		this.id = id;
		this.matricula = matricula;
		this.nombre = nombre;
		this.atraque = atraque;
		this.cuota = cuota;
		this.persona = persona;
	}
}
