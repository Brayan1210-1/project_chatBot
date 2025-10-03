package com.cesde.Chatbot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cesde.Chatbot.dto.CategoriaPreguntaRespuestaDTO;
import com.cesde.Chatbot.dto.PreguntaDTO;
import com.cesde.Chatbot.model.CategoriaPreguntaRespuesta;
import com.cesde.Chatbot.model.Pregunta;
import com.cesde.Chatbot.repository.CategoriaPreguntaRespuestaRepositorio;


@Service
public class CategoriaPreguntaRespuestaServicio {
	//METODOS BASICOS
    @Autowired
    private CategoriaPreguntaRespuestaRepositorio categoriaPreguntaResRepo;
    
    
    
    public List<PreguntaDTO> mostrarPreguntasPorCategoria(Long categoriaId){
    	List<Pregunta> preguntas = categoriaPreguntaResRepo.mostrarPreguntas(categoriaId);
    	
    	//convertir preguntas a DTO
    	return preguntas.stream()
    			.map(PreguntaDTO::desdeEntidad)
    			.collect(Collectors.toList());
    }
    
    public List<CategoriaPreguntaRespuestaDTO> obtenerTodasLasCategorias() {
    	
        List<CategoriaPreguntaRespuesta> categoriaPregunta = categoriaPreguntaResRepo.findAll();

        //convertir categorias a DTO
        return categoriaPregunta.stream()
                .map(CategoriaPreguntaRespuestaDTO::desdeEntidad)
                .collect(Collectors.toList());
    }

    public CategoriaPreguntaRespuestaDTO obtenerCategoriaPorId(Long id) {
        Optional<CategoriaPreguntaRespuesta> categoriaOpcional = categoriaPreguntaResRepo.findById(id);

        if (categoriaOpcional.isPresent()) {
            return CategoriaPreguntaRespuestaDTO.desdeEntidad(categoriaOpcional.get());
        }  else {
            throw new RuntimeException("Categoria no encontrado con ID: " + id);
        }
    }

    public CategoriaPreguntaRespuestaDTO crearCategoria(CategoriaPreguntaRespuestaDTO categoriaPreguntaRespuestaDTO) {

        //convertir dto a entidad
        CategoriaPreguntaRespuesta categoria = categoriaPreguntaRespuestaDTO.haciaEntidad();
        CategoriaPreguntaRespuesta categoriaGuardada = categoriaPreguntaResRepo.save(categoria);

        return CategoriaPreguntaRespuestaDTO.desdeEntidad(categoriaGuardada);
    }

    public CategoriaPreguntaRespuestaDTO actualizarCategoria(Long id, CategoriaPreguntaRespuestaDTO categoriaDTO) {
        Optional<CategoriaPreguntaRespuesta> categoriaOpcional = categoriaPreguntaResRepo.findById(id);

        if (categoriaOpcional.isPresent()) {
            CategoriaPreguntaRespuesta categoria = categoriaOpcional.get();

            //actualizar campos
            categoria.setNombre(categoriaDTO.getNombre());
            categoria.setDescripcion(categoriaDTO.getDescripcion());

            CategoriaPreguntaRespuesta categoriaActualizada = categoriaPreguntaResRepo.save(categoria);
            return CategoriaPreguntaRespuestaDTO.desdeEntidad(categoriaActualizada);
        } else {
            throw new RuntimeException("Categoria no encontrada con ID: " + id);
        }

    }

    public void eliminarCategoria(Long id) {
        if (categoriaPreguntaResRepo.existsById(id)) {
            categoriaPreguntaResRepo.deleteById(id);
        } else {
            throw new RuntimeException("No existe la categoria con id" + id);
        }
        
    }
}
