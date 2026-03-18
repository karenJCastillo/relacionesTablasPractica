package com.example.nuevo.Repository;

import com.example.nuevo.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Buscar por nombre
    List<Categoria> findByNombre(String nombre);

    // Verificar si existe por nombre
    boolean existsByNombre(String nombre);

}
