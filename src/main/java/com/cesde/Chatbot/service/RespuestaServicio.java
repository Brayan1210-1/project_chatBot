package com.cesde.Chatbot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesde.Chatbot.dto.RespuestaDTO;
import com.cesde.Chatbot.model.Pregunta;
import com.cesde.Chatbot.model.Respuesta;
import com.cesde.Chatbot.repository.PreguntaRepositorio;
import com.cesde.Chatbot.repository.RespuestaRepositorio;

@Service
public class RespuestaServicio {
	

	@Autowired
	private RespuestaRepositorio respuestaRepo;
	
	@Autowired
	private PreguntaRepositorio preguntaRepo;
	
	public RespuestaDTO crearRespuesta(RespuestaDTO respuestaDTO) {

		//se verifica si la pregunta existe
	Optional<Pregunta> preguntaOpcional = preguntaRepo.findById(respuestaDTO.getPreguntaId());
	if (preguntaOpcional.isEmpty()) {
		 throw new RuntimeException("No se encontró la pregunta con el id" + respuestaDTO.getPreguntaId());
	}
	
	Pregunta pregunta = preguntaOpcional.get();
        //convertir dto a entidad
        Respuesta respuesta = respuestaDTO.haciaEntidad(pregunta);
        Respuesta respuestaGuardada = respuestaRepo.save(respuesta);

        return RespuestaDTO.desdeEntidad(respuestaGuardada);
    }
	
	public RespuestaDTO actualizarRespuesta(Long id, RespuestaDTO respuestaDTO) {
		Optional<Respuesta> respuestaOpcional = respuestaRepo.findById(id);
				
				if (respuestaOpcional.isPresent()) {
				    Respuesta respuesta = respuestaOpcional.get();
				
			 
			 respuesta.setEnunciado(respuestaDTO.getEnunciado());
		    
		     Respuesta respuestaActualizada = respuestaRepo.save(respuesta);
		     return RespuestaDTO.desdeEntidad(respuestaActualizada);
				} else {
				throw new RuntimeException("Respuesta no encontrada con Id " + id);	
				}
			}
			
			public List<RespuestaDTO> obtenerTodasLasRespuestas() {
				List<Respuesta> respuestas = respuestaRepo.findAll();
			
				//convertir usuarios a DTO
				return respuestas.stream()
						.map(RespuestaDTO::desdeEntidad)
						.collect(Collectors.toList());
			}
			
			public void eliminarRespuesta(Long id) {
				if (respuestaRepo.existsById(id)) {
				   respuestaRepo.deleteById(id);
				} else {
					throw new RuntimeException("No existe la respuesta con id" + id);
				}
				
			  }
}
