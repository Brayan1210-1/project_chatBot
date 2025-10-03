package com.cesde.Chatbot.dto;

import com.cesde.Chatbot.model.CategoriaPreguntaRespuesta;
import com.cesde.Chatbot.model.Pregunta;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.NotBlank;

public class PreguntaDTO {
	private Long id;
	 
	@NotBlank(message = "El enunciado es obligatorio")
	 private String enunciado;
	
	@NotNull(message = "La categoria no puede ser nula")
	private Long categoriaId;
	 
	public PreguntaDTO() {}
	
	public PreguntaDTO(Long id, String enunciado, Long categoriaId) {
		this.id = id;
		this.enunciado = enunciado;
		this.categoriaId = categoriaId;
	}
	
	public static PreguntaDTO desdeEntidad(Pregunta pregunta) {
	    return new PreguntaDTO(
	      pregunta.getId(),
	      pregunta.getEnunciado(),
	      pregunta.getCategoria().getId()
	    );
	  }

	  public Pregunta haciaEntidad(CategoriaPreguntaRespuesta categoria) {
	    Pregunta pregunta = new Pregunta();
	    pregunta.setId(this.id);
	    pregunta.setEnunciado(this.enunciado);
	    pregunta.setCategoria(categoria);
	    
	    return pregunta;
	  }

	  public void ActualizarEntidad(Pregunta pregunta, CategoriaPreguntaRespuesta categoria) {
	    pregunta.setEnunciado(this.enunciado);
	    pregunta.setCategoria(categoria);
	  }	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
}
