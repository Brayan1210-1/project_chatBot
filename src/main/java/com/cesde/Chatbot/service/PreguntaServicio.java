package com.cesde.Chatbot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cesde.Chatbot.dto.PreguntaDTO;
import com.cesde.Chatbot.dto.RespuestaDTO;
import com.cesde.Chatbot.model.CategoriaPreguntaRespuesta;
import com.cesde.Chatbot.model.Pregunta;
import com.cesde.Chatbot.model.Respuesta;
import com.cesde.Chatbot.repository.CategoriaPreguntaRespuestaRepositorio;
import com.cesde.Chatbot.repository.PreguntaRepositorio;

@Service
public class PreguntaServicio {

	
	@Autowired
	private PreguntaRepositorio preguntaRepo;
	
	@Autowired
	private CategoriaPreguntaRespuestaRepositorio categoriaRepo;
	
	
	
	public PreguntaDTO crearPregunta(PreguntaDTO preguntaDTO) {

		//se verifica si la categoria existe
	Optional<CategoriaPreguntaRespuesta> categoriaOpcional = categoriaRepo.findById(preguntaDTO.getCategoriaId());
	if (categoriaOpcional.isEmpty()) {
		 throw new RuntimeException("No se encontró la categoría con el id" + preguntaDTO.getCategoriaId());
	}
	
	CategoriaPreguntaRespuesta categoria = categoriaOpcional.get();
        //convertir dto a entidad
        Pregunta pregunta = preguntaDTO.haciaEntidad(categoria);
        Pregunta preguntaGuardada = preguntaRepo.save(pregunta);

        return PreguntaDTO.desdeEntidad(preguntaGuardada);
    }
	
	public PreguntaDTO actualizarPregunta(Long id, PreguntaDTO preguntaDTO) {
Optional<Pregunta> preguntaOpcional = preguntaRepo.findById(id);
		
		if (preguntaOpcional.isPresent()) {
		    Pregunta pregunta = preguntaOpcional.get();
		
	 
	 pregunta.setEnunciado(preguntaDTO.getEnunciado());
    
     Pregunta preguntaActualizada = preguntaRepo.save(pregunta);
     return PreguntaDTO.desdeEntidad(preguntaActualizada);
		} else {
		throw new RuntimeException("Pregunta no encontrada con Id " + id);	
		}
	}
	
	public List<RespuestaDTO> mostrarRespuestasPorPregunta(Long preguntaId){
    	List<Respuesta> respuestas = preguntaRepo.mostrarRespuestas(preguntaId);
    	
    	//convertir preguntas a DTO
    	return respuestas.stream()
    			.map(RespuestaDTO::desdeEntidad)
    			.collect(Collectors.toList());
    }
	
	public List<PreguntaDTO> obtenerTodasLasPreguntas() {
		List<Pregunta> pregunta = preguntaRepo.findAll();
	
		//convertir usuarios a DTO
		return pregunta.stream()
				.map(PreguntaDTO::desdeEntidad)
				.collect(Collectors.toList());
	}
	
	public void eliminarPregunta(Long id) {
		if (preguntaRepo.existsById(id)) {
		   preguntaRepo.deleteById(id);
		} else {
			throw new RuntimeException("No existe la pregunta con id" + id);
		}
		
	  }
	
}
