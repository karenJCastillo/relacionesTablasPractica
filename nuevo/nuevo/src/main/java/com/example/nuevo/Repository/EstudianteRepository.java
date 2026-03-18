package com.example.nuevo.Repository;

import com.example.nuevo.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Buscar por correo
    List<Estudiante> findByCorreo(String correo);

    // Verificar si existe por correo
    boolean existsByCorreo(String correo);
}
