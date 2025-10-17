package com.cesde.Chatbot.model;

import java.util.ArrayList;
import java.util.List;

import com.cesde.Chatbot.superclass.Categoria;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria_pregunta_respuesta")
public class CategoriaPreguntaRespuesta extends Categoria {

	//Aún falta conectar esta clase con pregunta
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> preguntas = new ArrayList<Pregunta>();
	
	public CategoriaPreguntaRespuesta() {
		super();
	}
	
	
	public CategoriaPreguntaRespuesta(String nombre, String descripcion) {
		super(nombre, descripcion);
	}
	
	public void añadirPregunta(Pregunta pregunta) {
		preguntas.add(pregunta);
		pregunta.setCategoria(this);
	}
	
	public void eliminarPregunta(Pregunta pregunta) {
		preguntas.remove(pregunta);
		pregunta.setCategoria(null);
	}
	
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	
}
