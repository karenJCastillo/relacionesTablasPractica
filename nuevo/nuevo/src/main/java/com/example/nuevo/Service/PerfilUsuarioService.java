package com.example.nuevo.Service;

import com.example.nuevo.Model.PerfilUsuario;
import java.util.List;

public interface PerfilUsuarioService {
    List<PerfilUsuario> listarTodos();
    PerfilUsuario buscarPorId(Long id);
    PerfilUsuario buscarPorUsuarioId(Long usuarioId);
    PerfilUsuario guardar(PerfilUsuario perfil);
    PerfilUsuario actualizar(Long id, PerfilUsuario datos);
    void eliminar(Long id);
}