package com.cesde.Chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesde.Chatbot.model.Sugerencia;

@Repository
public interface SugerenciaRepositorio extends JpaRepository<Sugerencia, Long> {
  //Perdóname por todo JpaRepository 
}
