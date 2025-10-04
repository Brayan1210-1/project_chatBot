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

import com.cesde.Chatbot.dto.SugerenciaDTO;
import com.cesde.Chatbot.dto.UsuarioDTO;
import com.cesde.Chatbot.service.SugerenciaServicio;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sugerencias")
@Tag(name = "Gestión de sugerencias", description = "Métodos crud para sugerencias")
public class SugerenciaControlador {
	
	@Autowired
	private SugerenciaServicio sugerenciaServicio;
	
	@GetMapping
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Se obtuvieron las sugerencias"),
		@ApiResponse(responseCode = "500", description = "El servidor no pudo responder")
	})
	public ResponseEntity<List<SugerenciaDTO>> obtenerSugerencias (){
		try {
			List<SugerenciaDTO> sugerencia = sugerenciaServicio.obtenerTodasLasSugerencias();
			return ResponseEntity.ok(sugerencia);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Sugerencia creada correctamente"),
			@ApiResponse(responseCode = "400", description = "Introduzca los campos necesarios")
	})
	public ResponseEntity<SugerenciaDTO> crearPregunta (@Valid @RequestBody SugerenciaDTO sugerenciaDTO, UsuarioDTO usuario){
		try {
			SugerenciaDTO sugerenciaCreada = sugerenciaServicio.crearSugerencia(sugerenciaDTO, usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(sugerenciaCreada);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sugerencia actualizada correctamente"),
			@ApiResponse(responseCode = "404", description = "No se pudo encontrar la pregunta")
	})
	public ResponseEntity<SugerenciaDTO> actualizarSugerencia(
		     @Parameter(description = "ID de la sugerenia", required = true, example = "1")
		     @PathVariable Long id,
		     @Valid @RequestBody SugerenciaDTO sugerenciaDTO) {
		 try {
		     SugerenciaDTO sugerenciaActualizada = sugerenciaServicio.actualizarSugerencia(id, sugerenciaDTO);
		     return ResponseEntity.ok(sugerenciaActualizada);
		 } catch (RuntimeException e) {
		     return ResponseEntity.notFound().build();
		 }
		}

	@DeleteMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Pregunta eliminada"),
			@ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
	})
	 public ResponseEntity<Void> borrarSugerencia(
	            @Parameter(description = "ID de la sugerencia", required = true, example = "1")
	            @PathVariable Long id) {

	        try {
	            sugerenciaServicio.eliminarSugerencia(id);
	            return ResponseEntity.noContent().build();
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
        }
	
	
	
}
