package com.example.nuevo.Service;

import com.example.nuevo.Model.Usuario;
import com.example.nuevo.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        List<Usuario> usuarios = usuarioRepository.findByCorreo(correo);
        if (!usuarios.isEmpty()) {
            return usuarios.get(0);
        }
        throw new RuntimeException("Usuario no encontrado con correo: " + correo);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Long id, Usuario datos) {
        Usuario usuario = buscarPorId(id);
        usuario.setNombre(datos.getNombre());
        usuario.setCorreo(datos.getCorreo());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }
}