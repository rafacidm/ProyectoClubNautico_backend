package com.sistema.club.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "barcos")
public class Barco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_barco")
	private Long id;
	@Column(name="matricula_barco", nullable = false, unique=true)
	private String matricula;
	@Column(name="nombre_barco", nullable = false, unique=true)
	private String nombre;
	@Column(name="atraque_barco", nullable = false, unique=true)
	private Integer atraque;
	@Column(name="cuota_barco", nullable = false)
	private Double cuota;
	
	@JsonBackReference(value="persona-barco")
	@OneToOne
	@JoinColumn(name="id_propietario", referencedColumnName = "id_persona")
	private Persona persona;
	@JsonManagedReference(value="barco-salida")
	@OneToMany(mappedBy="barco", orphanRemoval = true)
	private List<Salida> salidas = new ArrayList<Salida>();
		
	public Barco(Long id, String matricula, String nombre, Integer atraque, Double cuota, Persona persona) {
		this.id = id;
		this.matricula = matricula;
		this.nombre = nombre;
		this.atraque = atraque;
		this.cuota = cuota;
		this.persona = persona;
	}
	
	@Override
	public String toString() {
		return "Barco [id=" + id + ", matricula=" + matricula + ", nombre=" + nombre + ", atraque=" + atraque
				+ ", cuota=" + cuota + ", persona=" + persona + "]";
	}
}
