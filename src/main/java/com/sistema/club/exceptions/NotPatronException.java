package com.sistema.club.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NotPatronException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String nombreRecurso;
	private String nombreCampo;
	private String idCampo;
	
	public NotPatronException(String nombreRecurso, String nombreCampo, String idCampo) {
		super(String.format("%s con %s '%s' no tiene titulo de patron", nombreRecurso, nombreCampo, idCampo));
		this.nombreRecurso = nombreRecurso;
		this.nombreCampo = nombreCampo;
		this.idCampo = idCampo;
	}

	public String getNombreRecurso() {
		return nombreRecurso;
	}

	public void setNombreRecurso(String nombreRecurso) {
		this.nombreRecurso = nombreRecurso;
	}

	public String getNombreCampo() {
		return nombreCampo;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	public String getIdCampo() {
		return idCampo;
	}

	public void setIdCampo(String idCampo) {
		this.idCampo = idCampo;
	}
}
