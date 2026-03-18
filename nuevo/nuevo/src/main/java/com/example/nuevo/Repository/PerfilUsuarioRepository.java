package com.example.nuevo.Repository;

import com.example.nuevo.Model.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long> {

    // Buscar por documento
    List<PerfilUsuario> findByDocumento(String documento);

    // Buscar el perfil de un usuario específico
    List<PerfilUsuario> findByUsuarioId(Long usuarioId);
}
