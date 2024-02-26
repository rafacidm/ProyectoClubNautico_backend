package com.sistema.club.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sistema.club.dto.ErrorDetalles;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetalles> manejarResourceNotFoundException(ResourceNotFoundException exception, WebRequest wr){
		ErrorDetalles detalles = new ErrorDetalles(new Date(), exception.getMessage(), wr.getDescription(false));
		return new ResponseEntity<>(detalles, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ClubAppException.class)
	public ResponseEntity<ErrorDetalles> manejarClubAppException(ClubAppException exception, WebRequest wr){
		ErrorDetalles detalles = new ErrorDetalles(new Date(), exception.getMessage(), wr.getDescription(false));
		return new ResponseEntity<>(detalles, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotPatronException.class)
	public ResponseEntity<ErrorDetalles> manejarNotPatronException(NotPatronException exception, WebRequest wr){
		ErrorDetalles detalles = new ErrorDetalles(new Date(), exception.getMessage(), wr.getDescription(false));
		return new ResponseEntity<>(detalles, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetalles> manejarGlobalException(Exception exception, WebRequest wr){
		ErrorDetalles detalles = new ErrorDetalles(new Date(), exception.getMessage(), wr.getDescription(false));
		return new ResponseEntity<>(detalles, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errores = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String nombreCampo = ((FieldError)error).getField();
			String mensaje = error.getDefaultMessage();
			errores.put(nombreCampo, mensaje);
		});
		return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
	}
}
