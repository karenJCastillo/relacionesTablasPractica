package com.example.nuevo.Service;

import com.example.nuevo.Model.PerfilUsuario;
import com.example.nuevo.Model.Usuario;
import com.example.nuevo.Repository.PerfilUsuarioRepository;
import com.example.nuevo.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilUsuarioServiceImpl implements PerfilUsuarioService {

    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final UsuarioRepository usuarioRepository;

    public PerfilUsuarioServiceImpl(PerfilUsuarioRepository perfilUsuarioRepository, UsuarioRepository usuarioRepository) {
        this.perfilUsuarioRepository = perfilUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<PerfilUsuario> listarTodos() {
        return perfilUsuarioRepository.findAll();
    }

    @Override
    public PerfilUsuario buscarPorId(Long id) {
        return perfilUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con id: " + id));
    }

    @Override
    public PerfilUsuario buscarPorUsuarioId(Long usuarioId) {
        List<PerfilUsuario> perfiles = perfilUsuarioRepository.findByUsuarioId(usuarioId);
        if (!perfiles.isEmpty()) {
            return perfiles.get(0);
        }
        throw new RuntimeException("Perfil no encontrado para el usuario con id: " + usuarioId);
    }

    @Override
    public PerfilUsuario guardar(PerfilUsuario perfil) {
        if (perfil.getUsuario() != null && perfil.getUsuario().getId() != null) {
            Long usuarioId = perfil.getUsuario().getId();
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + usuarioId));
            perfil.setUsuario(usuario);  // ← reemplaza el objeto parcial con el completo de la BD
        }
        return perfilUsuarioRepository.save(perfil);
    }

    @Override
    public PerfilUsuario actualizar(Long id, PerfilUsuario datos) {
        PerfilUsuario perfil = buscarPorId(id);
        perfil.setDocumento(datos.getDocumento());
        perfil.setTelefono(datos.getTelefono());
        if (datos.getUsuario() != null && datos.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(datos.getUsuario().getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            perfil.setUsuario(usuario);
        }
        return perfilUsuarioRepository.save(perfil);
    }

    @Override
    public void eliminar(Long id) {
        if (!perfilUsuarioRepository.existsById(id)) {
            throw new RuntimeException("Perfil no encontrado con id: " + id);
        }
        perfilUsuarioRepository.deleteById(id);
    }
}