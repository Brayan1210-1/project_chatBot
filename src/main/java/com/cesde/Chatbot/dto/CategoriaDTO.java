package com.cesde.Chatbot.dto;

import com.cesde.Chatbot.model.Categoria;
import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private static String nombre;

    private static String descripcion;

    public CategoriaDTO(Long id, String nombre, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public static CategoriaDTO fromEntity(Categoria categoria){
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
    public Categoria toEntity(){
        Categoria categoria = new Categoria();
        categoria.setId(this.id);
        categoria.setNombre(this.nombre);
        categoria.setDescripcion(this.descripcion);
        return categoria;
    }
    public void updateEntity(Categoria categoria) {
        categoria.setNombre(this.nombre);
        categoria.setDescripcion(this.descripcion);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

