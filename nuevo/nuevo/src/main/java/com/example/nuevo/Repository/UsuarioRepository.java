package com.example.nuevo.Repository;

import com.example.nuevo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar por correo
    List<Usuario> findByCorreo(String correo);

    // Verificar si existe por correo
    boolean existsByCorreo(String correo);
}
