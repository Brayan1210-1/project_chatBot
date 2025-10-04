package com.cesde.Chatbot.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria_sugerencia")
public class CategoriaSugerencia extends Categoria {

	@OneToMany(mappedBy = "categoriaSug", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Sugerencia> sugerencias = new ArrayList<>();
	
	public CategoriaSugerencia() { }
	
	public CategoriaSugerencia(String nombre, String descripcion) {
		super(nombre, descripcion);
	}

	public List<Sugerencia> getSugerencias() {
		return sugerencias;
	}

	public void setSugerencias(List<Sugerencia> sugerencias) {
		this.sugerencias = sugerencias;
	}
}
