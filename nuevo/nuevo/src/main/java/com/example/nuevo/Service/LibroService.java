package com.example.nuevo.Service;

import com.example.nuevo.Model.Libro;
import java.util.List;

public interface LibroService {
    List<Libro> listarTodos();
    Libro buscarPorId(Long id);
    List<Libro> listarPorCategoria(Long categoriaId);
    List<Libro> listarPorAutor(String autor);
    Libro guardar(Libro libro);
    Libro actualizar(Long id, Libro datos);
    void eliminar(Long id);
}