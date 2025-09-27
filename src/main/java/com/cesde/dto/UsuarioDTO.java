package com.cesde.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//comenzando a rezar para que esto sirva
public class UsuarioDTO {

	private Long id;
	
	@NotBlank(message = "Se necesita un nombre de usuario")
	private String nombre;
	
	@NotBlank(message = "Ingrese un email")
	@Email(message = "Ingrese un email válido")
	private String email;
	
	
	private String telefono;
	
	public UsuarioDTO () { }
	
	public UsuarioDTO(Long id, String nombre, String email, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		
	}
	
	
	

}
