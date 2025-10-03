package com.cesde.Chatbot.dto;

import com.cesde.Chatbot.model.Pregunta;
import com.cesde.Chatbot.model.Respuesta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RespuestaDTO {

	//Se hace como si fuera una clase normal y se tiene que colocar el atributo de la relación
	/**
	 * NotBlank se usa para STRINGS
	 * NotNull se usa para Longs
	 */
	private Long id;
	
	@NotBlank(message = "El enunciado no puede estar vacío")
	private String enunciado;
	
	@NotNull(message = "La pregunta no puede ser nula")
	private Long preguntaId;
	
	public RespuestaDTO() {	}
	
	public RespuestaDTO(Long id, String enunciado, Long preguntaId) {
		this.id = id;
		this.enunciado = enunciado;
		this.preguntaId = preguntaId;
	}

	//Métodos para map
	public static RespuestaDTO desdeEntidad(Respuesta respuesta) {
	    return new RespuestaDTO(
	      respuesta.getId(),
	      respuesta.getEnunciado(),
	      respuesta.getPregunta().getId()
	    );
	  }

	  public Respuesta haciaEntidad(Pregunta pregunta) {
	    Respuesta respuesta = new Respuesta();
	    respuesta.setId(this.id);
	    respuesta.setEnunciado(this.enunciado);
	    respuesta.setPregunta(pregunta);
	    
	    return respuesta;
	  }

	  public void ActualizarEntidad(Respuesta respuesta, Pregunta pregunta) {
	    respuesta.setEnunciado(this.enunciado);
	    respuesta.setPregunta(pregunta);
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

	public Long getPreguntaId() {
		return preguntaId;
	}

	public void setPreguntaId(Long preguntaId) {
		this.preguntaId = preguntaId;
	}
	
}
