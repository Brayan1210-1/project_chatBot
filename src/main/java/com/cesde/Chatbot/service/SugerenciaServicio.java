package com.cesde.Chatbot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesde.Chatbot.repository.UsuarioRepositorio;
import com.cesde.Chatbot.dto.SugerenciaDTO;
import com.cesde.Chatbot.dto.UsuarioDTO;
import com.cesde.Chatbot.model.CategoriaSugerencia;
import com.cesde.Chatbot.model.Sugerencia;
import com.cesde.Chatbot.model.Usuario;
import com.cesde.Chatbot.repository.CategoriaSugerenciaRepositorio;
import com.cesde.Chatbot.repository.SugerenciaRepositorio;

@Service
public class SugerenciaServicio {

	@Autowired
	private SugerenciaRepositorio sugerenciaRepo;
	
	@Autowired
	private CategoriaSugerenciaRepositorio categoriaRepo;
	
	@Autowired
	private UsuarioRepositorio usuarioRepo;
	
	
	public SugerenciaDTO crearSugerencia(SugerenciaDTO sugerenciaDTO, UsuarioDTO usuarioDTO) {

		//verificar si categoria sugerencia y el usuario existe
	Optional<CategoriaSugerencia> categoriaOpcional = categoriaRepo.findById(sugerenciaDTO.getCategoriaSugerenciaId());
	Optional<Usuario> usuarioOpcional = usuarioRepo.findById(sugerenciaDTO.getUsuarioId());
	if (categoriaOpcional.isEmpty() || usuarioOpcional.isEmpty()) {
		 throw new RuntimeException("No se encontró la categoría con el id" + sugerenciaDTO.getCategoriaSugerenciaId() + "o el usuario con el id: "
				 + sugerenciaDTO.getUsuarioId());
	}
	
	CategoriaSugerencia categoria = categoriaOpcional.get();
	Usuario usuario = usuarioOpcional.get();
        //convertir dto a entidad
        Sugerencia sugerencia = sugerenciaDTO.haciaEntidad(categoria, usuario);
        Sugerencia sugerenciaGuardada = sugerenciaRepo.save(sugerencia);

        return SugerenciaDTO.desdeEntidad(sugerenciaGuardada);
    }
	
	public SugerenciaDTO actualizarSugerencia(Long id, SugerenciaDTO sugerenciaDTO) {
Optional<Sugerencia> sugerenciaOpcional = sugerenciaRepo.findById(id);
		
		if (sugerenciaOpcional.isPresent()) {
		    Sugerencia sugerencia = sugerenciaOpcional.get();
		
	 
	 sugerencia.setEnunciado(sugerenciaDTO.getEnunciado());
    
     Sugerencia sugerenciaActualizada = sugerenciaRepo.save(sugerencia);
     return SugerenciaDTO.desdeEntidad(sugerenciaActualizada);
		} else {
		throw new RuntimeException("Sugerenc no encontrada con el Id " + id);	
		}
	}
	
	
	public List<SugerenciaDTO> obtenerTodasLasSugerencias() {
		List<Sugerencia> sugerencia = sugerenciaRepo.findAll();
	
		//convertir usuarios a DTO
		return sugerencia.stream()
				.map(SugerenciaDTO::desdeEntidad)
				.collect(Collectors.toList());
	}
	
	public void eliminarSugerencia(Long id) {
		if (sugerenciaRepo.existsById(id)) {
		   sugerenciaRepo.deleteById(id);
		} else {
			throw new RuntimeException("No existe la pregunta con id" + id);
		}
		
	  }
	
}
