package com.cesde.Chatbot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cesde.Chatbot.dto.CategoriaDTO;
import com.cesde.Chatbot.model.Categoria;
import com.cesde.Chatbot.repository.CategoriaRepositorio;

@Service
public class CategoriaServicio {

    //METODOS BASICOS
    @Autowired
    private CategoriaRepositorio categoriaRepo;

    public List<CategoriaDTO> obtenerTodosLosUsuarios() {
        List<Categoria> usuarios = categoriaRepo.findAll();

        //convertir categorias a DTO
        return Categorias.stream()
                .map(CategoriaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public CategoriaDTO obtenerUsuarioPorId(Long id) {
        Optional<Categoria> categoriaOpcional = categoriaRepo.findById(id);

        if (categoriaOpcional.isPresent()) {
            return CategoriaDTO.fromEntity(categoriaOpcional.get());
        }  else {
            throw new RuntimeException("Categoria no encontrado con ID: " + id);
        }
    }

    public CategoriaDTO crearUsuario(CategoriaDTO categoriaDTO) {


        //convertir dto a entidad
        Categoria categoria = categoriaDTO.toEntity();
        Categoria categoriaGuardada = categoriaRepo.save(categoria);

        return categoriaDTO.fromEntity(categoriaGuardada);
    }

    public CategoriaDTO actualizarUsuario(Long id, CategoriaDTO usuarioDTO) {
        Optional<Categoria> categoriaOpcional = categoriaRepo.findById(id);

        if (categoriaOpcional.isPresent()) {
            Categoria categoria = categoriaOpcional.get();

            //actualizar campos
            categoria.setNombre(CategoriaDTO.getNombre());
            categoria.setDescripcion(CategoriaDTO.getDescripcion());

            Categoria CategoriaActualizada = categoriaRepo.save(categoria);
            return CategoriaDTO.fromEntity(CategoriaActualizada);
        } else {
            throw new RuntimeException("Categoria no encontrada con ID: " + id);
        }

    }

    public void eliminarCategoria(Long id) {
        if (categoriaRepo.existsById(id)) {
            categoriaRepo.deleteById(id);
        } else {
            throw new RuntimeException("No existe la categoria con id" + id);
        }

    }



}
