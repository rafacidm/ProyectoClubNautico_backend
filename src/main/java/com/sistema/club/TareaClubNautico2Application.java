package com.sistema.club;

import java.time.LocalDate;
import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sistema.club.dao.BarcoDAO;
import com.sistema.club.dao.PersonaDAO;
import com.sistema.club.dao.SalidaDAO;
import com.sistema.club.dao.UsuarioDAO;
import com.sistema.club.model.Barco;
import com.sistema.club.model.Persona;
import com.sistema.club.model.Rol;
import com.sistema.club.model.Salida;
import com.sistema.club.model.Usuario;

@SpringBootApplication
public class TareaClubNautico2Application implements CommandLineRunner{

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Autowired
	private UsuarioDAO usuarioDao;
	@Autowired
	private PersonaDAO personaDao;
	@Autowired
	private BarcoDAO barcoDao;
	@Autowired
	private SalidaDAO salidaDao;
	
	public static void main(String[] args) {
		SpringApplication.run(TareaClubNautico2Application.class, args);
	}
	
	@Override
	public void run(String... args){
		Usuario adminAccount = usuarioDao.findByRol(Rol.ADMIN);
		if(adminAccount == null) {
			Usuario usuario = new Usuario();
			usuario.setEmail("admin@club.com");
			usuario.setApellidos("Nautico");
			usuario.setNombre("Club");
			usuario.setUsername("ADMIN");
			usuario.setRol(Rol.ADMIN);
			usuario.setPassword(new BCryptPasswordEncoder().encode("1234"));
			usuarioDao.save(usuario);
		}
		
		Persona s1 = new Persona(1L, "Rafael", "Cid Medina", "45995357A", "610028213", true, true, null);
		Persona s2 = new Persona(2L, "Antonio", "Lopez Rodriguez","54879621B", "641258741", true, false, null);
		Persona s3 = new Persona(3L, "Fernando", "Garcia Garcia","42178541C", "741256124", true, true, null);
		Persona s4 = new Persona(4L, "Luisa", "Martinez Fernandez","64312345D", "741256124", false, true, null);
		Persona s5 = new Persona(5L, "María", "Sánchez Gómez","23412365E", "741256124", true, false, null);
		
		personaDao.save(s1);
		personaDao.save(s2);
		personaDao.save(s3);
		personaDao.save(s4);
		personaDao.save(s5);
		
		Barco b1 = new Barco(1L, "SE456445", "POPEYE", 154, 450.57, s1);
		Barco b2 = new Barco(2L, "LU478871", "MARINO", 234, 342.99, s2);
		Barco b3 = new Barco(3L, "HU587234", "EL PIRATA", 543, 864.45, s3);
		
		barcoDao.save(b1);
		barcoDao.save(b2);
		barcoDao.save(b3);
		
		Salida e1 = new Salida(1L, b2, LocalDate.of(2023, 8, 23), LocalTime.of(17, 35), "Cadiz", s1);
		Salida e2 = new Salida(2L, b1, LocalDate.of(2023, 7, 25), LocalTime.of(14, 22), "Tenerife", s1);
		Salida e3 = new Salida(3L, b3, LocalDate.of(2023, 7, 22), LocalTime.of(18, 53), "Las Palmas", s3);
		Salida e4 = new Salida(4L, b1, LocalDate.of(2023, 7, 14), LocalTime.of(9, 14), "Tarifa", s3);
		Salida e5 = new Salida(5L, b3, LocalDate.of(2023, 6, 11), LocalTime.of(20, 03), "Gibraltar", s1);
		
		salidaDao.save(e1);
		salidaDao.save(e2);
		salidaDao.save(e3);
		salidaDao.save(e4);
		salidaDao.save(e5);
	}

}
