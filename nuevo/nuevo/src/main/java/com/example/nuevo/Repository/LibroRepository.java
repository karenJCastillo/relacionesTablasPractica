package com.example.nuevo.Repository;

import com.example.nuevo.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libros por autor
    List<Libro> findByAutor(String autor);

    // Buscar libros por categoría
    List<Libro> findByCategoriaId(Long categoriaId);

    // Buscar libros por año de publicación
    List<Libro> findByAnioPublicacion(int anioPublicacion);
}