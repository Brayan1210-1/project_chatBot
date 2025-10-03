package com.cesde.Chatbot.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cesde.Chatbot.model.CategoriaPreguntaRespuesta;
import com.cesde.Chatbot.model.Pregunta;

//pensando en como implementar una sola tabla para las categorias
@Repository
public interface CategoriaPreguntaRespuestaRepositorio extends JpaRepository<CategoriaPreguntaRespuesta, Long> {
	
    Optional<CategoriaPreguntaRespuesta> findByDescripcion(String descripcion);

    // Verificar si existe una categoria con esa descripcion
    boolean existsByDescripcion(String descripcion);

    // Buscar categorias por su descripcion (ignorando mayúsculas/minúsculas)
    List<CategoriaPreguntaRespuesta> findByDescripcionContainingIgnoreCase(String descripcion);

    // Buscar categorias ordenadas por descripcion
    List<CategoriaPreguntaRespuesta> findAllByOrderByDescripcionAsc();

    // Buscar categorias creados después de una fecha
    List<CategoriaPreguntaRespuesta> findByCreatedAtAfter(LocalDateTime date);

    
    //traer todas las preguntas de la categoría
  @Query("SELECT p FROM Pregunta p WHERE p.categoria.id = :categoriaId")
    List<Pregunta> mostrarPreguntas(@Param("categoriaId") Long categoriaId);
    
    
    // Métodos con paginación
    // Buscar categorias con paginación automática
    // Buscar por categoria con paginación

    //Page<CategoriaPreguntaRespuesta> findByCategoriaContaining(String categoria, Pageable pageable);
}
