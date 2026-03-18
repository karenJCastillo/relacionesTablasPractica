package com.example.nuevo.Repository;

import com.example.nuevo.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Buscar por nombre
    List<Curso> findByNombre(String nombre);

    // Verificar si existe por nombre
    boolean existsByNombre(String nombre);
}
