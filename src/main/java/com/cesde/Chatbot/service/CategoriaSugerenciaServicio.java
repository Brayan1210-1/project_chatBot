package com.cesde.Chatbot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesde.Chatbot.dto.CategoriaSugerenciaDTO;
import com.cesde.Chatbot.dto.SugerenciaDTO;
import com.cesde.Chatbot.model.CategoriaSugerencia;
import com.cesde.Chatbot.repository.CategoriaSugerenciaRepositorio;
import com.cesde.Chatbot.model.Sugerencia;

@Service
public class CategoriaSugerenciaServicio {
	//METODOS BASICOS
	
    @Autowired
    private CategoriaSugerenciaRepositorio categoriaSugRepositorio;
    
    
    
    public List<SugerenciaDTO> mostrarSugerenciasPorCategoria(Long categoriaId){
    	List<Sugerencia> sugerencias = categoriaSugRepositorio.mostrarSugerencias(categoriaId);
    	
    	//convertir preguntas a DTO
    	return sugerencias.stream()
    			.map(SugerenciaDTO::desdeEntidad)
    			.collect(Collectors.toList());
    }
    
    public List<CategoriaSugerenciaDTO> obtenerTodasLasCategorias() {
    	
        List<CategoriaSugerencia> categoriasSugerencia = categoriaSugRepositorio.findAll();

        //convertir categorias a DTO
        return categoriasSugerencia.stream()
                .map(CategoriaSugerenciaDTO::desdeEntidad)
                .collect(Collectors.toList());
    }

    public CategoriaSugerenciaDTO obtenerCategoriaPorId(Long id) {
        Optional<CategoriaSugerencia> categoriaOpcional = categoriaSugRepositorio.findById(id);

        if (categoriaOpcional.isPresent()) {
            return CategoriaSugerenciaDTO.desdeEntidad(categoriaOpcional.get());
        }  else {
            throw new RuntimeException("Categoria no encontrado con ID: " + id);
        }
    }

    public CategoriaSugerenciaDTO crearCategoria(CategoriaSugerenciaDTO categoriaSugDTO) {

        //convertir dto a entidad
        CategoriaSugerencia categoriaSug = categoriaSugDTO.haciaEntidad();
        CategoriaSugerencia categoriaSugGuardada = categoriaSugRepositorio.save(categoriaSug);

        return CategoriaSugerenciaDTO.desdeEntidad(categoriaSugGuardada);
    }

    public CategoriaSugerenciaDTO actualizarCategoria(Long id, CategoriaSugerenciaDTO categoriaSugDTO) {
        Optional<CategoriaSugerencia> categoriaOpcional = categoriaSugRepositorio.findById(id);

        if (categoriaOpcional.isPresent()) {
            CategoriaSugerencia categoria = categoriaOpcional.get();

            //actualizar campos
            categoria.setNombre(categoriaSugDTO.getNombre());
            categoria.setDescripcion(categoriaSugDTO.getDescripcion());

            CategoriaSugerencia categoriaActualizada = categoriaSugRepositorio.save(categoria);
            return CategoriaSugerenciaDTO.desdeEntidad(categoriaActualizada);
        } else {
            throw new RuntimeException("Categoria no encontrada con ID: " + id);
        }

    }

    public void eliminarCategoria(Long id) {
        if (categoriaSugRepositorio.existsById(id)) {
            categoriaSugRepositorio.deleteById(id);
        } else {
            throw new RuntimeException("No existe la categoria con id" + id);
        }
        
    }
}
