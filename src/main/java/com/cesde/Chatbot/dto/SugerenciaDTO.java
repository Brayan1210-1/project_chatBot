package com.cesde.Chatbot.dto;

import com.cesde.Chatbot.model.CategoriaSugerencia;
import com.cesde.Chatbot.model.Sugerencia;
import com.cesde.Chatbot.model.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SugerenciaDTO {
private Long id;
	
	@NotBlank(message = "El enunciado no puede estar vacío")
	private String enunciado;
	
	@NotNull(message = "La  no puede ser nula")
	private Long categoriaSugerenciaId;
	
	@NotNull(message = "El id del usuario no puede ser nulo")
	private Long usuarioId;
	
	public SugerenciaDTO() {	}
	
	public SugerenciaDTO(Long id, String enunciado, Long categoriaSugerenciaId, Long usuarioId) {
		this.id = id;
		this.enunciado = enunciado;
		this.categoriaSugerenciaId = categoriaSugerenciaId;
		this.usuarioId = usuarioId;
	}

	
	public static SugerenciaDTO desdeEntidad(Sugerencia sugerencia) {
	    return new SugerenciaDTO(
	      sugerencia.getId(),
	      sugerencia.getEnunciado(),
	      sugerencia.getCategoriaSug().getId(),
	      sugerencia.getUsuario().getId()
	    );
	  }

	  public Sugerencia haciaEntidad(CategoriaSugerencia categoriaSug, Usuario usuario) {
	    Sugerencia sugerencia = new Sugerencia();
	    sugerencia.setId(this.id);
	    sugerencia.setEnunciado(this.enunciado);
	    sugerencia.setCategoriaSug(categoriaSug);
	    sugerencia.setUsuario(usuario);
	    
	    return sugerencia;
	  }

	  //No veo fiable que se pueda cambiar el id del usuario que la hizo
	  public void ActualizarEntidad(Sugerencia sugerencia, CategoriaSugerencia catSug) {
	    sugerencia.setEnunciado(this.enunciado);
	    sugerencia.setCategoriaSug(catSug);
	  }

	  public Long getId() {
		  return id;
	  }

	  public String getEnunciado() {
		  return enunciado;
	  }

	  public Long getCategoriaSugerenciaId() {
		  return categoriaSugerenciaId;
	  }

	  public void setId(Long id) {
		  this.id = id;
	  }

	  public void setEnunciado(String enunciado) {
		  this.enunciado = enunciado;
	  }

	  public void setCategoriaSugerenciaId(Long categoriaSugerenciaId) {
		  this.categoriaSugerenciaId = categoriaSugerenciaId;
	  }

	  public Long getUsuarioId() {
		  return usuarioId;
	  }

	  public void setUsuarioId(Long usuarioId) {
		  this.usuarioId = usuarioId;
	  }	
}
