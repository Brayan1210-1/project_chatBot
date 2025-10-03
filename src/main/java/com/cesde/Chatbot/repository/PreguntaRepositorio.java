package com.cesde.Chatbot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cesde.Chatbot.model.Pregunta;
import com.cesde.Chatbot.model.Respuesta;

@Repository
public interface PreguntaRepositorio extends JpaRepository<Pregunta, Long> {

	//En proceso...
	Optional<Pregunta> findByEnunciado(String enunciado);
	
	//traer todas las respuestas de una pregunta
	@Query("SELECT r FROM Respuesta r WHERE r.pregunta.id =:preguntaId")
	List<Respuesta> mostrarRespuestas(@Param("preguntaId")Long preguntaId);
}
