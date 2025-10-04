package com.cesde.Chatbot.dto;

import java.util.ArrayList;
import java.util.List;

import com.cesde.Chatbot.model.CategoriaSugerencia;

import jakarta.validation.constraints.NotBlank;

public class CategoriaSugerenciaDTO {
	private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    private List<SugerenciaDTO> sugerencias = new ArrayList<>();
    
    public CategoriaSugerenciaDTO() {}
    
    public CategoriaSugerenciaDTO(Long id, String nombre, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public static CategoriaSugerenciaDTO desdeEntidad(CategoriaSugerencia categoriaSugerencia){
        return new CategoriaSugerenciaDTO(
                categoriaSugerencia.getId(),
                categoriaSugerencia.getNombre(),
                categoriaSugerencia.getDescripcion()
        );
    }
    
    public CategoriaSugerencia haciaEntidad(){
        CategoriaSugerencia categoriaSugerencia = new CategoriaSugerencia();
        categoriaSugerencia.setId(this.id);
        categoriaSugerencia.setNombre(this.nombre);
        categoriaSugerencia.setDescripcion(this.descripcion);
        return categoriaSugerencia;
    }
    
    
    public void actualizarEntidad(CategoriaSugerencia categoriaSugerencia) {
        categoriaSugerencia.setNombre(this.nombre);
        categoriaSugerencia.setDescripcion(this.descripcion);
    }

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public List<SugerenciaDTO> getSugerencias() {
		return sugerencias;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setSugerencias(List<SugerenciaDTO> sugerencias) {
		this.sugerencias = sugerencias;
	}

}
