package com.cesde.Chatbot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cesde.Chatbot.dto.UsuarioDTO;
import com.cesde.Chatbot.model.Usuario;
import com.cesde.Chatbot.repository.UsuarioRepositorio;

@Service
public class UsuarioServicio {
	
	//METODOS BASICOS
	@Autowired
	private UsuarioRepositorio usuarioRepo;
	
	public List<UsuarioDTO> obtenerTodosLosUsuarios() {
		List<Usuario> usuarios = usuarioRepo.findAll();
	
		//convertir usuarios a DTO
		return usuarios.stream()
				.map(UsuarioDTO::desdeEntidad)
				.collect(Collectors.toList());
	}

	public UsuarioDTO obtenerUsuarioPorId(Long id) {
		Optional<Usuario> usuarioOpcional = usuarioRepo.findById(id);
		
		if (usuarioOpcional.isPresent()) {
			return UsuarioDTO.desdeEntidad(usuarioOpcional.get());
		}  else {
			throw new RuntimeException("Usuario no encontrado con ID: " + id);
		}
	}
	
	public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
		
		//verificar que el correo no exista
		if (usuarioRepo.existsByEmail(usuarioDTO.getEmail())) {
		throw new RuntimeException("Ya existe un usuario con el email" + usuarioDTO.getEmail());
		}
		//convertir dto a entidad
		Usuario usuario = usuarioDTO.haciaEntidad();
		Usuario usuarioGuardado = usuarioRepo.save(usuario);
		
		return UsuarioDTO.desdeEntidad(usuarioGuardado);
	}
	
	public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
		Optional<Usuario> usuarioOpcional = usuarioRepo.findById(id);
		
		if (usuarioOpcional.isPresent()) {
			Usuario usuario = usuarioOpcional.get();
		
	      //verificar que el email no exista
	 if (!usuario.getEmail().equals(usuarioDTO.getEmail()) && 
			   usuarioRepo.existsByEmail(usuarioDTO.getEmail()) ) {
     throw new RuntimeException("Ya existe un usuario con el email: " + usuarioDTO.getEmail());
	       }
	 //actualizar campos
	 usuario.setNombre(usuarioDTO.getNombre());
     usuario.setEmail(usuarioDTO.getEmail());
     usuario.setTelefono(usuarioDTO.getTelefono());
    
     Usuario usuarioActualizado = usuarioRepo.save(usuario);
     return UsuarioDTO.desdeEntidad(usuarioActualizado);
		} else {
		throw new RuntimeException("Usuario no encontrado con ID: " + id);	
		}
			
	}
	
    public void eliminarUsuario(Long id) {
		if (usuarioRepo.existsById(id)) {
		   usuarioRepo.deleteById(id);
		} else {
			throw new RuntimeException("No existe el usuario con id" + id);
		}
		
	  }
	
	
	
	}

