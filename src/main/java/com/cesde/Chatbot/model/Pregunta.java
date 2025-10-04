package com.cesde.Chatbot.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pregunta")
public class Pregunta extends Contenido {

	@ManyToOne
    @JoinColumn(name = "categoria_pregunta_respuesta_id", nullable = false)
    private CategoriaPreguntaRespuesta categoria;
	
	@OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Respuesta> respuestas = new ArrayList<Respuesta>();
	
	public Pregunta() { super(); }
	
	public Pregunta( String enunciado, CategoriaPreguntaRespuesta categoria) {
		super( enunciado);
		this.categoria = categoria;
	}

	public CategoriaPreguntaRespuesta getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaPreguntaRespuesta categoria) {
		this.categoria = categoria;
	}

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}
}
