package com.example.nuevo.Controller;

import com.example.nuevo.Model.Estudiante;
import com.example.nuevo.Service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> listar() {
        return ResponseEntity.ok(estudianteService.listarTodos());
    }

    @GetMapping("/{id}/buscar")
    public ResponseEntity<Estudiante> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(estudianteService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping ("/{id}/crear")
    public ResponseEntity<Estudiante> crear(@RequestBody Estudiante estudiante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.guardar(estudiante));
    }

    @PutMapping("/{id}/actualizar")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.ok(estudianteService.actualizar(id, estudiante));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            estudianteService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/cursos/{cursoId}/inscribirCurso")
    public ResponseEntity<Estudiante> inscribirCurso(
            @PathVariable Long id,
            @PathVariable Long cursoId) {
        try {
            return ResponseEntity.ok(estudianteService.inscribirCurso(id, cursoId));
        } catch (RuntimeException e) {
            String msg = e.getMessage();
            if (msg != null && msg.contains("ya está inscrito")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/cursos/{cursoId}/retirarCurso")
    public ResponseEntity<Estudiante> retirarCurso(
            @PathVariable Long id,
            @PathVariable Long cursoId) {
        try {
            return ResponseEntity.ok(estudianteService.retirarCurso(id, cursoId));
        } catch (RuntimeException e) {
            String msg = e.getMessage();
            if (msg != null && msg.contains("no está inscrito")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return ResponseEntity.notFound().build();
        }
    }
}