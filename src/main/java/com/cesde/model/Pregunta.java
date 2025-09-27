package com.cesde.model;


import com.cesde.plantillas.Contenido;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "preguntas")
public class Pregunta extends Contenido {
	
	 @ManyToOne(fetch =  FetchType.LAZY)
	 @JoinColumn(name = "categoria_pregunta_id")
	 private CategoriaPregunta categoria;

	public Pregunta() { }
	
	public Pregunta(Long id, String enunciado, CategoriaPregunta categoriaPregunta) {
		this.categoria = categoriaPregunta;
		this.setId(id);
		this.setEnunciado(enunciado);
		this.categoria = categoriaPregunta;
	}

	public CategoriaPregunta getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaPregunta categoria) {
		this.categoria = categoria;
	}
}
