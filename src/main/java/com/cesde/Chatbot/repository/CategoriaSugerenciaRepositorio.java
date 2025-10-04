package com.cesde.Chatbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cesde.Chatbot.model.CategoriaSugerencia;
import com.cesde.Chatbot.model.Sugerencia;

@Repository
public interface CategoriaSugerenciaRepositorio extends JpaRepository<CategoriaSugerencia, Long> {

	//Y a comenzar con las consultas :)
	 //traer todas las sugerencias de la categoría
	 @Query("SELECT s FROM Sugerencia s WHERE s.categoriaSug.id = :categoriaSugId")
	    List<Sugerencia> mostrarSugerencias(@Param("categoriaSugId") Long categoriaSugId);
	  
	  //Después meto más cosas
}
