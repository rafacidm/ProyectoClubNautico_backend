package com.sistema.club.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "personas", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni_persona"})})
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_persona")
	private Long id;
	@Column(name = "nombre_persona", nullable = false)
	private String nombre;
	@Column(name = "apellidos_persona", nullable = false)
	private String apellidos;
	@Column(name = "dni_persona", nullable = false)
	private String dni;
	@Column(name = "telefono_persona", nullable = false)
	private String telefono;
	@Column(name = "esSocio_persona")
	private Boolean esSocio;
	@Column(name = "esPatron_persona")
	private Boolean esPatron;
	
	
	@JsonManagedReference(value="persona-barco")
	@OneToOne(mappedBy = "persona")
	private Barco barco;
	
	
	@JsonManagedReference(value="persona-salida")
	@OneToMany(mappedBy="patron", fetch = FetchType.LAZY)
	private List<Salida> salidas = new ArrayList<Salida>();
	
	public Persona(Long id, String nombre, String apellidos, String dni, String telefono, Boolean esSocio,
			Boolean esPatron, Barco barco) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.esSocio = esSocio;
		this.esPatron = esPatron;
		this.barco = barco;
	}
}
