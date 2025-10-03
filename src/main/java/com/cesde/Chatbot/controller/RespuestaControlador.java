package com.cesde.Chatbot.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.cesde.Chatbot.dto.RespuestaDTO;
import com.cesde.Chatbot.service.RespuestaServicio;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/respuestas")
@Tag(name = "Gestión de respuestas", description = "Métodos para administrar las respuestas")
public class RespuestaControlador {
	@Autowired
	private RespuestaServicio respuestaServicio;
	
	@GetMapping
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Se obtuvieron las respuestas"),
		@ApiResponse(responseCode = "500", description = "El servidor no pudo responder")
	})
	public ResponseEntity<List<RespuestaDTO>> obtenerRespuestas (){
		try {
			List<RespuestaDTO> respuestas = respuestaServicio.obtenerTodasLasRespuestas();
			return ResponseEntity.ok(respuestas);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Respuesta creada correctamente"),
			@ApiResponse(responseCode = "400", description = "Introduzca los campos necesarios")
	})
	public ResponseEntity<RespuestaDTO> crearRespuesta (@Valid @RequestBody RespuestaDTO respuestaDTO){
		try {
			RespuestaDTO respuestaCreada = respuestaServicio.crearRespuesta(respuestaDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(respuestaCreada);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pregunta actualizada correctamente"),
			@ApiResponse(responseCode = "404", description = "No se pudo encontrar la pregunta")
	})
	public ResponseEntity<RespuestaDTO> actualizarRespuesta(
		     @Parameter(description = "ID de la respuesta", required = true, example = "1")
		     @PathVariable Long id,
		     @Valid @RequestBody RespuestaDTO respuestaDTO) {
		 try {
		     RespuestaDTO respuestaActualizada = respuestaServicio.actualizarRespuesta(id, respuestaDTO);
		     return ResponseEntity.ok(respuestaActualizada);
		 } catch (RuntimeException e) {
		     return ResponseEntity.notFound().build();
		 }
		}

	@DeleteMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Pregunta eliminada"),
			@ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
	})
	 public ResponseEntity<Void> borrarRespuesta(
	            @Parameter(description = "ID de la respuesta", required = true, example = "1")
	            @PathVariable Long id) {

	        try {
	            respuestaServicio.eliminarRespuesta(id);
	            return ResponseEntity.noContent().build();
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
        }
	
	
	
}
