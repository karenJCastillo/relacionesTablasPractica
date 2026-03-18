package com.example.nuevo.Service;

import com.example.nuevo.Model.Estudiante;
import java.util.List;

public interface EstudianteService {
    List<Estudiante> listarTodos();
    Estudiante buscarPorId(Long id);
    Estudiante buscarPorCorreo(String correo);
    Estudiante guardar(Estudiante estudiante);
    Estudiante actualizar(Long id, Estudiante datos);
    void eliminar(Long id);
    Estudiante inscribirCurso(Long estudianteId, Long cursoId);
    Estudiante retirarCurso(Long estudianteId, Long cursoId);
}