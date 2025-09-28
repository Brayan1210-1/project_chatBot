package com.cesde.Chatbot.repository;

import com.cesde.Chatbot.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


// CategoriaRepositorio - Interfaz para acceder a los datos de categorias
@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {


    // Buscar categorias por descripcion
    Optional<Categoria> findByDescripcion(String descripcion);

    // Verificar si existe una categoria con esa descripcion
    boolean existsByDescripcion(String descripcion);


    // Buscar categorias por su descripcion (ignorando mayúsculas/minúsculas)
    List<Categoria> findByDescripcionContainingIgnoreCase(String descripcion);

    // Buscar categorias ordenadas por descripcion
    List<Categoria> findAllByOrderByDescripcionAsc();

    // Buscar categorias creados después de una fecha
    List<Categoria> findByCreatedAtAfter(LocalDateTime date);

    // Métodos con paginación

    // Buscar categorias con paginación automática
    // Buscar por categoria con paginación

    Page<Categoria> findByCategoriaContaining(String categoria, Pageable pageable);
}

