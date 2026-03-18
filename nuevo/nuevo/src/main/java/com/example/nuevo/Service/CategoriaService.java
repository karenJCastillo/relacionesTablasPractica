package com.example.nuevo.Service;

import com.example.nuevo.Model.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> listarTodas();
    Categoria buscarPorId(Long id);
    Categoria guardar(Categoria categoria);
    Categoria actualizar(Long id, Categoria datos);
    void eliminar(Long id);
    boolean existePorNombre(String nombre);
}