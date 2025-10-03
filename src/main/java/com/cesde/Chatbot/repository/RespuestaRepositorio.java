package com.cesde.Chatbot.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesde.Chatbot.model.CategoriaPreguntaRespuesta;
import com.cesde.Chatbot.model.Pregunta;
import com.cesde.Chatbot.model.Respuesta;

@Repository
public interface RespuestaRepositorio extends JpaRepository<Respuesta, Long>{

	//hay que aprovechar los autogenerados del jpa
	List<CategoriaPreguntaRespuesta> findByCreatedAtAfter(LocalDateTime date);
	
	Optional<Pregunta> findByEnunciado(String enunciado);
	
}
