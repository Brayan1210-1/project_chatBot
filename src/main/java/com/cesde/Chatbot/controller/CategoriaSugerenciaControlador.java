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

import com.cesde.Chatbot.dto.CategoriaSugerenciaDTO;
import com.cesde.Chatbot.dto.SugerenciaDTO;
import com.cesde.Chatbot.service.CategoriaSugerenciaServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/catsugerencias")
@Tag(name = "Gestión de categorias sugerencia", description = "Métodos para adminstrar las categorias")
public class CategoriaSugerenciaControlador {

	@Autowired
	private CategoriaSugerenciaServicio categoriaServicio;
	
	@GetMapping
	@Operation( summary = "Obtener categorias")
	@ApiResponses(value = {
		 @ApiResponse(responseCode = "200", description = "Fue realizada correctamente"),
		 @ApiResponse(responseCode = "500", description = "Error del servidor")
	})
	public ResponseEntity<List<CategoriaSugerenciaDTO>> obtenerCategorias (){
		try {
			List<CategoriaSugerenciaDTO> categorias = categoriaServicio.obtenerTodasLasCategorias();
			return ResponseEntity.ok(categorias);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@PostMapping
	@Operation(summary = "Crear una categoría")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Categoria creada"),
			@ApiResponse(responseCode = "400", description = "Esta categoría ya existe")
			})
	public ResponseEntity<CategoriaSugerenciaDTO> crearCategoria (@RequestBody CategoriaSugerenciaDTO categoriaSug){
	try {
		CategoriaSugerenciaDTO categoriaSugCreada = categoriaServicio.crearCategoria(categoriaSug);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSugCreada);
	} catch (RuntimeException e) {
		return ResponseEntity.badRequest().build();
	}
	}
	
	@PutMapping("/{id}")
	 @Operation(summary = "Actualizar una categoria",
     description = "Actualiza la información de una categoria")
public ResponseEntity<CategoriaSugerenciaDTO> actualizarCategoria(
     @Parameter(description = "ID de la categoria", required = true, example = "1")
     @PathVariable Long id,
     @Valid @RequestBody CategoriaSugerenciaDTO categoriaSugDTO) {
 try {
     CategoriaSugerenciaDTO categoriaSugActualizada = categoriaServicio.actualizarCategoria(id, categoriaSugDTO);
     return ResponseEntity.ok(categoriaSugActualizada);
 } catch (RuntimeException e) {
     return ResponseEntity.notFound().build();
 }
}
	
	@DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoria",
            description = "Elimina una categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria eliminada"),
            @ApiResponse(responseCode = "404", description = "Categotia no encontrada")
    })
    public ResponseEntity<Void> borrarCategoria(
            @Parameter(description = "ID de la categoria", required = true, example = "1")
            @PathVariable Long id) {

        try {
            categoriaServicio.eliminarCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
	}

	
	
	@GetMapping("/{id}/sugerencias")
	public ResponseEntity<List<SugerenciaDTO>> obtenerPreguntasPorCategoria(
            @PathVariable("id") Long categoriaId) {
        List<SugerenciaDTO> sugerencias = categoriaServicio.mostrarSugerenciasPorCategoria(categoriaId);

        if (sugerencias.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(sugerencias); 
    }
    
}
