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
import com.cesde.Chatbot.dto.UsuarioDTO;
import com.cesde.Chatbot.service.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Gestión de usuarios", description = "métodos crud para los usuarios")
public class UsuarioControlador {

	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@GetMapping
	@Operation(summary = "Obtener todos los usuario")
	@ApiResponses(value = {
	       @ApiResponse(responseCode = "200", description = "operación exitosa"),
	       @ApiResponse(responseCode = "500", description = "Error del servidor")
	})
	public ResponseEntity<List<UsuarioDTO>> obtenerTodosLosUsuarios(){
		try {
		List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodosLosUsuarios();
		return ResponseEntity.ok(usuarios);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@PostMapping
    @Operation(summary = "Crear nuevo usuario",
            description = "Crea un usuario con la información proporcionada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o email ya existe")
    })
    public ResponseEntity<UsuarioDTO> crearUsuario(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información del nuevo usuario", required = true)
            @Valid @RequestBody UsuarioDTO usuarioDTO) {

        try {
            UsuarioDTO usuarioCreado = usuarioServicio.crearUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
	
	@PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario completo",
            description = "Actualiza toda la información del usuario")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(
            @Parameter(description = "ID del usuario", required = true, example = "1")
            @PathVariable Long id,

            @Valid @RequestBody UsuarioDTO userDTO) {

        try {
            UsuarioDTO usuarioActualizado = usuarioServicio.actualizarUsuario(id, userDTO);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
	
	 @DeleteMapping("/{id}")
	    @Operation(summary = "Eliminar usuario",
	            description = "Elimina un usuario del sistema")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
	            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	    })
	    public ResponseEntity<Void> borrarUsuario(
	            @Parameter(description = "ID del usuario", required = true, example = "1")
	            @PathVariable Long id) {

	        try {
	            usuarioServicio.eliminarUsuario(id);
	            return ResponseEntity.noContent().build();
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
