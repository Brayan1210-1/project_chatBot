package com.cesde.Chatbot.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Se necesita un nombre de usuario")
	@Column(name = "nombre", nullable = false, length = 80)
	private String nombre;
	
	@NotBlank(message = "Ingrese un email")
	@Email
	@Column(name = "email", nullable = false, length = 50)
	private String email;
	
	@Column(name = "telefono", length = 15)
	private String telefono;
	
	@Column(name = "createdAt", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updatedAt", nullable = false)
	private LocalDateTime updatedAt;

	//Aún falta hacer la relación con la clase sugerencia
	
	public Usuario() {}
	
	public Usuario(Long id, String nombre, String email, String telefono ) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	 @PrePersist
	  protected void onCreate() {
	    this.createdAt = LocalDateTime.now();
	    this.updatedAt = LocalDateTime.now();
	  }

	  @PreUpdate
	  protected void onUpdate() {
	    this.updatedAt = LocalDateTime.now();
	  }
	
	
	public long getId() {
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
