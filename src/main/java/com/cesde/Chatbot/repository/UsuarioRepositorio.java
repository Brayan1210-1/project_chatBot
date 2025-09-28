package com.cesde.Chatbot.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cesde.Chatbot.model.Usuario;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>  {

	//buscar usuario por email
	Optional<Usuario> findByEmail(String email);
	
	//Saber si un usuario existe por email
    boolean existsByEmail(String email);
    
    /*
     * buscar usuario por nombre
    List<Usuario> findByNameContainingIgnoreCase(String nombre);*/
    
    //Buscar usuarios creados después de una fecha
    List<Usuario> findByCreatedAtAfter(LocalDateTime date);
    
    
    
}
