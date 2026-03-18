package com.example.nuevo.Service;

import com.example.nuevo.Model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> listarTodos();
    Usuario buscarPorId(Long id);
    Usuario buscarPorCorreo(String correo);
    Usuario guardar(Usuario usuario);
    Usuario actualizar(Long id, Usuario datos);
    void eliminar(Long id);
    boolean existePorCorreo(String correo);
}