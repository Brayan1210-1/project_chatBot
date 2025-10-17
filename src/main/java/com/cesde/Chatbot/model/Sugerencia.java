package com.cesde.Chatbot.model;

import com.cesde.Chatbot.superclass.Contenido;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sugerencia")
public class Sugerencia extends Contenido {

	@ManyToOne
	@JoinColumn(name = "categoria_sugerencia_id", nullable = false)
	private CategoriaSugerencia categoriaSug;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	public Sugerencia() {}
	
	public Sugerencia(String enunciado, CategoriaSugerencia categoriaSug) {
		super(enunciado);
		this.categoriaSug = categoriaSug;
	}

	public CategoriaSugerencia getCategoriaSug() {
		return categoriaSug;
	}

	public void setCategoriaSug(CategoriaSugerencia categoriaSug) {
		this.categoriaSug = categoriaSug;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
