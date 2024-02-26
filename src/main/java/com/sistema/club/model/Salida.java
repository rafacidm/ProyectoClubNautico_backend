package com.sistema.club.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "salidas")
public class Salida implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_salida")
	private Long id;
	@Column(name="fecha_salida", nullable = false)
	private LocalDate fecha;
	@Column(name="hora_salida", nullable = false)
	private LocalTime hora;
	@Column(name="destino_salida", nullable = false)
	private String destino;
	
	
	@JsonBackReference(value="persona-salida")
	@ManyToOne
	@JoinColumn(name="idPatron_salida", nullable = false)
	private Persona patron;
	
	@JsonBackReference(value="barco-salida")
	@ManyToOne
	@JoinColumn(name="idBarco_salida", nullable = false)
	private Barco barco;
	
	public Salida(Long id, Barco barco, LocalDate fecha, LocalTime hora, String destino, Persona patron) {
		this.id = id;
		this.barco = barco;
		this.fecha = fecha;
		this.hora = hora;
		this.destino = destino;
		this.patron = patron;
	}
	

	@Override
	public String toString() {
		return "Salida [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", destino=" + destino + ", patron="
				+ patron + ", barco=" + barco + "]";
	}
}
