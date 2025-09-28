package com.cesde.Chatbot.dto;

import com.cesde.Chatbot.model.Usuario;

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
	
	public static UsuarioDTO desdeEntidad(Usuario usuario) {
		return new UsuarioDTO (
		  usuario.getId(),
		  usuario.getNombre(),
		  usuario.getEmail(),
		  usuario.getTelefono()
		  );
		
	}
	public Usuario haciaEntidad() {
		Usuario usuario = new Usuario();
		usuario.setId(this.id);
		usuario.setNombre(this.nombre);
		usuario.setEmail(this.email);
		usuario.setTelefono(this.telefono);
		return usuario;
	}
	
	public void actualizarEntidad(Usuario usuario) {
		usuario.setNombre(this.nombre);
		usuario.setEmail(this.email);
		usuario.setTelefono(this.telefono);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	

}
