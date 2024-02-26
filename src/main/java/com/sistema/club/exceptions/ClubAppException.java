package com.sistema.club.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubAppException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus estado;
	private String mensaje;
	
	public ClubAppException(HttpStatus estado, String mensaje) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
	}
	
	public ClubAppException(HttpStatus estado, String mensaje, String mensaje2) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;		
		this.mensaje = mensaje2;
	}

	@Override
	public String toString() {
		return "ClubAppException [estado=" + estado + ", mensaje=" + mensaje + "]";
	}
}
