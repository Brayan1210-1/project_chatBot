package com.cesde.Chatbot.dto;


import java.util.ArrayList;
import java.util.List;

import com.cesde.Chatbot.model.CategoriaPreguntaRespuesta;
import jakarta.validation.constraints.NotBlank;

public class CategoriaPreguntaRespuestaDTO {
	private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    private List<PreguntaDTO> preguntas = new ArrayList<>();
    
    public CategoriaPreguntaRespuestaDTO() {}
    
    public CategoriaPreguntaRespuestaDTO(Long id, String nombre, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public static CategoriaPreguntaRespuestaDTO desdeEntidad(CategoriaPreguntaRespuesta categoriaPregunta){
        return new CategoriaPreguntaRespuestaDTO(
                categoriaPregunta.getId(),
                categoriaPregunta.getNombre(),
                categoriaPregunta.getDescripcion()
        );
    }
    
    public CategoriaPreguntaRespuesta haciaEntidad(){
        CategoriaPreguntaRespuesta categoriaPregunta = new CategoriaPreguntaRespuesta();
        categoriaPregunta.setId(this.id);
        categoriaPregunta.setNombre(this.nombre);
        categoriaPregunta.setDescripcion(this.descripcion);
        return categoriaPregunta;
    }
    
    
    public void actualizarEntidad(CategoriaPreguntaRespuesta categoriaPregunta) {
        categoriaPregunta.setNombre(this.nombre);
        categoriaPregunta.setDescripcion(this.descripcion);
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<PreguntaDTO> getPreguntas() {
		return preguntas;
	}
 
	public void añadirPregunta(PreguntaDTO pregunta) {
		preguntas.add(pregunta);
	}
	
}
