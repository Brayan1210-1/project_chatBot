package com.cesde.model;

import java.util.ArrayList;
import java.util.List;

import com.cesde.plantillas.Categoria;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria_pregunta")
public class CategoriaPregunta extends Categoria {
	
	@OneToMany(mappedBy = "categoria_pregunta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<Pregunta> preguntas = new ArrayList<>();
	
public CategoriaPregunta() {
	super();
}

public CategoriaPregunta(String nombre, String descripcion) {
	super(nombre, descripcion);
}

public void añadirPregunta(Pregunta pregunta) {
	preguntas.add(pregunta);
	pregunta.setCategoria(this);
}

}
