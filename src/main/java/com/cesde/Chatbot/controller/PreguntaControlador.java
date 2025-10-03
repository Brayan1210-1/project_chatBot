package com.cesde.Chatbot.controller;

import org.springframework.web.bind.annotation.RestController;
import com.cesde.Chatbot.dto.PreguntaDTO;
import com.cesde.Chatbot.dto.RespuestaDTO;
import com.cesde.Chatbot.service.PreguntaServicio;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//Aún falta acomodar los cuerpos para las preguntas 

@RestController
@RequestMapping("/api/preguntas")
@Tag(name = "Gestión de preguntas", description = "Métodos para administrar las preguntas")
public class PreguntaControlador {

	@Autowired
	private PreguntaServicio preguntaServicio;
	
	@GetMapping
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Se obtuvieron las preguntas"),
		@ApiResponse(responseCode = "500", description = "El servidor no pudo responder")
	})
	public ResponseEntity<List<PreguntaDTO>> obtenerPreguntas (){
		try {
			List<PreguntaDTO> preguntas = preguntaServicio.obtenerTodasLasPreguntas();
			return ResponseEntity.ok(preguntas);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Pregunta creada correctamente"),
			@ApiResponse(responseCode = "400", description = "Introduzca los campos necesarios")
	})
	public ResponseEntity<PreguntaDTO> crearPregunta (@Valid @RequestBody PreguntaDTO preguntaDTO){
		try {
			PreguntaDTO preguntaCreada = preguntaServicio.crearPregunta(preguntaDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(preguntaCreada);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pregunta actualizada correctamente"),
			@ApiResponse(responseCode = "404", description = "No se pudo encontrar la pregunta")
	})
	public ResponseEntity<PreguntaDTO> actualizarPregunta(
		     @Parameter(description = "ID de la pregunta", required = true, example = "1")
		     @PathVariable Long id,
		     @Valid @RequestBody PreguntaDTO preguntaDTO) {
		 try {
		     PreguntaDTO preguntaActualizada = preguntaServicio.actualizarPregunta(id, preguntaDTO);
		     return ResponseEntity.ok(preguntaActualizada);
		 } catch (RuntimeException e) {
		     return ResponseEntity.notFound().build();
		 }
		}

	@DeleteMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Pregunta eliminada"),
			@ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
	})
	 public ResponseEntity<Void> borrarPregunta(
	            @Parameter(description = "ID de la pregunta", required = true, example = "1")
	            @PathVariable Long id) {

	        try {
	            preguntaServicio.eliminarPregunta(id);
	            return ResponseEntity.noContent().build();
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
        }
	
	
	@GetMapping("/{id}/respuestas")
	public ResponseEntity<List<RespuestaDTO>> obtenerRespuestasPorPregunta(
            @PathVariable("id") Long preguntaid) {
        List<RespuestaDTO> respuestas = preguntaServicio.mostrarRespuestasPorPregunta(preguntaid);

        if (respuestas.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(respuestas); 
    }
	
	
}