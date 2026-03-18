package com.example.nuevo.Service;

import com.example.nuevo.Model.Curso;
import java.util.List;

public interface CursoService {
    List<Curso> listarTodos();
    Curso buscarPorId(Long id);
    Curso buscarPorNombre(String nombre);
    Curso guardar(Curso curso);
    Curso actualizar(Long id, Curso datos);
    void eliminar(Long id);
    boolean existePorNombre(String nombre);
}